package com.acme.learning.platform.learning.domain.services;

import com.acme.learning.platform.learning.domain.model.commands.CancelEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.ConfirmEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.RejectEnrollmentCommand;
import com.acme.learning.platform.learning.domain.model.commands.RequestEnrollmentCommand;

/**
 * EnrollmentCommandService
 *
 * <p>
 *     This interface defines the contract for the command service that handles the enrollment commands.
 *     The command service is responsible for handling the commands and dispatching them to the appropriate
 *     aggregate.
 * </p>
 */
public interface EnrollmentCommandService {
    /**
     * Handles the request enrollment command.
     * @param command The request enrollment command.
     * @return The enrollment id.
     * @see RequestEnrollmentCommand
     */
    Long handle(RequestEnrollmentCommand command);

    /**
     * Handles the confirm enrollment command.
     * @param command The confirm enrollment command.
     * @return The enrollment id.
     * @see ConfirmEnrollmentCommand
     */
    Long handle(ConfirmEnrollmentCommand command);

    /**
     * Handles the reject enrollment command.
     * @param command The reject enrollment command.
     * @return The enrollment id.
     * @see RejectEnrollmentCommand
     */
    Long handle(RejectEnrollmentCommand command);

    /**
     * Handles the cancel enrollment command.
     * @param command The cancel enrollment command.
     * @return The enrollment id.
     * @see CancelEnrollmentCommand
     */
    Long handle(CancelEnrollmentCommand command);
}
