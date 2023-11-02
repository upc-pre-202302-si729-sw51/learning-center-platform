package com.acme.learning.platform.learning.application.internal.commandservices;

import com.acme.learning.platform.learning.domain.exceptions.CourseNotFoundException;
import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.platform.learning.domain.model.aggregates.Enrollment;
import com.acme.learning.platform.learning.domain.model.commands.CancelEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.ConfirmEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.RejectEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.RequestEnrollmentCommand;
import com.acme.learning.platform.learning.domain.services.EnrollmentCommandService;
import com.acme.learning.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import com.acme.learning.platform.learning.infrastructure.persistence.jpa.repositories.EnrollmentRepository;
import com.acme.learning.platform.learning.infrastructure.persistence.jpa.repositories.StudentRepository;
import org.springframework.stereotype.Service;

/**
 * This is the implementation of the EnrollmentCommandService interface.
 * It is responsible for handling the commands related to the Enrollment aggregate.
 * It uses the EnrollmentRepository to save the Enrollment aggregate.
 */
@Service
public class EnrollmentCommandServiceImpl implements EnrollmentCommandService {
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentCommandServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Long handle(RequestEnrollmentCommand command) {
        studentRepository.findByAcmeStudentRecordId(command.acmeStudentRecordId())
                .map(student -> {
            Course course = courseRepository.findById(command.courseId())
                    .orElseThrow(() -> new CourseNotFoundException(command.courseId()));
            Enrollment enrollment = new Enrollment(command.acmeStudentRecordId(), course);
            enrollmentRepository.save(enrollment);
            return enrollment.getId();
        }).orElseThrow(() -> new RuntimeException("Student not found"));
        return 0L;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Long handle(ConfirmEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId())
                .map(enrollment -> {
                    enrollment.confirm();
                    enrollmentRepository.save(enrollment);
                    return enrollment.getId();
                }).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Long handle(RejectEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId())
                .map(enrollment -> {
                    enrollment.reject();
                    enrollmentRepository.save(enrollment);
                    return enrollment.getId();
                }).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return null;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Long handle(CancelEnrollmentCommand command) {
        enrollmentRepository.findById(command.enrollmentId())
                .map(enrollment -> {
                    enrollment.cancel();
                    enrollmentRepository.save(enrollment);
                    return enrollment.getId();
                }).orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return null;
    }
}
