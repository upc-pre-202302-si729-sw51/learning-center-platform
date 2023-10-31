package com.acme.learning.platform.learning.domain.services;

import com.acme.learning.platform.learning.domain.model.aggregates.Course;
import com.acme.learning.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.platform.learning.domain.model.commands.DeleteCourseCommand;
import com.acme.learning.platform.learning.domain.model.commands.UpdateCourseCommand;

import java.util.Optional;

public interface CourseCommandService {
    Long handle(CreateCourseCommand command);
    Optional<Course> handle(UpdateCourseCommand command);
    void handle(DeleteCourseCommand command);
}
