package com.acme.learning.platform.learning.domain.services;

import com.acme.learning.platform.learning.domain.model.commands.CreateCourseCommand;
import com.acme.learning.platform.learning.domain.model.commands.UpdateCourseCommand;

public interface CourseCommandService {
    Long handle(CreateCourseCommand command);
    Long handle(UpdateCourseCommand command);
}
