package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.commands.SeedRolesCommand;

/**
 * RoleCommandService
 * This interface is used to handle the commands related to the role domain entity.
 */
public interface RoleCommandService {
    /**
     * handle SeedRolesCommand
     * This method is used to seed default roles to database.
     * @param command the SeedRolesCommand
     * @see SeedRolesCommand
     */
    void handle(SeedRolesCommand command);
}
