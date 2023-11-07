package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.aggregates.User;
import com.acme.learning.platform.iam.domain.model.commands.SignInCommand;
import com.acme.learning.platform.iam.domain.model.commands.SignUpCommand;

import java.util.Optional;

/**
 * User command service.
 * <p>
 *     This service is responsible for handling user commands.
 * </p>
 */
public interface UserCommandService {
    /**
     * This method handles the {@link SignUpCommand} command.
     *
     * @param command the {@link SignUpCommand} command.
     * @return the {@link User} entity.
     */
    Optional<User> handle(SignUpCommand command);
    /**
     * This method handles the {@link SignInCommand} command.
     *
     * @param command the {@link SignInCommand} command.
     * @return the {@link User} entity.
     */
    Optional<User> handle(SignInCommand command);
}
