package com.acme.learning.platform.learning.application.internal.commandservices;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learning.platform.learning.domain.services.CourseCommandService;
import com.acme.learning.platform.learning.infrastructure.persistence.jpa.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseCommandServiceImpl implements CourseCommandService {
    private final CourseRepository courseRepository;

    public CourseCommandServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Long handle(CreateCourseCommand command) {
        if (courseRepository.existsByTitle(command.title())) {
            throw new IllegalArgumentException("Course already exists");
        }
        var course = new Course(command.title(), command.description());
        courseRepository.save(course);
        return course.getId();
    }

    @Override
    public Long handle(UpdateCourseCommand command) {
        courseRepository.findById(command.id()).map(courseToUpdate -> {
            var courseWithSameTitle = courseRepository.findByTitle(command.title());
            if (courseWithSameTitle.isPresent() && !courseWithSameTitle.get().getId().equals(command.id())) {
                throw new IllegalArgumentException("Course with same title already exists");
            }
            courseToUpdate.setTitle(command.title());
            courseToUpdate.setDescription(command.description());
            courseRepository.save(courseToUpdate);
            return courseToUpdate.getId();
        }).orElseThrow(() -> new IllegalArgumentException("Course not found"));
        return 0L;
    }

}
