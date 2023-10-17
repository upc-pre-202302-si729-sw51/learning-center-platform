package com.acme.learning.platform.learning.domain.model.aggregates;

import com.acme.learning.platform.learning.domain.model.valueobjects.AcmeStudentRecordId;
import com.acme.learning.platform.learning.domain.model.valueobjects.ProfileId;
import com.acme.learning.platform.learning.domain.model.valueobjects.StudentPerformanceMetricSet;
import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @Column(name = "acme_student_id")
    private AcmeStudentRecordId acmeStudentRecordId;

    @Embedded
    private ProfileId profileId;

    @Embedded
    private StudentPerformanceMetricSet performanceMetricSet;


    public Student() {
        this.acmeStudentRecordId = new AcmeStudentRecordId();
        this.performanceMetricSet = new StudentPerformanceMetricSet();
    }

    public Student(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public void updateMetricsOnCourseCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalCompletedCourses();
    }

    public void updateMetricsOnTutorialCompleted() {
        this.performanceMetricSet = this.performanceMetricSet.incrementTotalTutorials();
    }

    public String getStudentRecordId() {
        return this.acmeStudentRecordId.studentRecordId();
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

}
