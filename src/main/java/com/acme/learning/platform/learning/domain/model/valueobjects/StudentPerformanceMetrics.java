package com.acme.learning.platform.learning.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record StudentPerformanceMetrics(Integer totalCompletedCourses, Integer totalTutorials) {
    public StudentPerformanceMetrics() {
        this(0, 0);
    }

    public StudentPerformanceMetrics {
        if (totalCompletedCourses < 0) {
            throw new IllegalArgumentException("Total completed courses cannot be negative");
        }

        if (totalTutorials < 0) {
            throw new IllegalArgumentException("Total tutorials cannot be negative");
        }
    }

    public StudentPerformanceMetrics incrementTotalCompletedCourses() {
        return new StudentPerformanceMetrics(totalCompletedCourses + 1, totalTutorials);
    }

    public StudentPerformanceMetrics incrementTotalTutorials() {
        return new StudentPerformanceMetrics(totalCompletedCourses, totalTutorials + 1);
    }
}
