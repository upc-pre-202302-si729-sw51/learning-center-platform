package com.acme.learning.platform.learning.domain.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long courseId) {
        super("Course with Id " + courseId + "not found");
    }
}
