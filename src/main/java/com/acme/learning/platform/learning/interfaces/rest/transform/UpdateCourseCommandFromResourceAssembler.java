package com.acme.learning.platform.learning.interfaces.rest.transform;

import com.acme.learning.platform.learning.domain.model.commands.UpdateCourseCommand;
import com.acme.learning.platform.learning.interfaces.rest.resources.UpdateCourseResource;

public class UpdateCourseCommandFromResourceAssembler {
    public static UpdateCourseCommand toCommandFromResource(UpdateCourseResource resource) {
        return new UpdateCourseCommand(resource.id(), resource.title(), resource.description());
    }
}
