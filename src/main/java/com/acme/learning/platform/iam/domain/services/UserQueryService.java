package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.aggregates.User;
import com.acme.learning.platform.iam.domain.model.queries.GetAllUsersQuery;
import com.acme.learning.platform.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

/**
 * UserQueryService is the interface that defines the contract for the User Query Service.
 * The User Query Service is responsible for handling all the queries related to the User Aggregate.
 */
public interface UserQueryService {
    /**
     * This method handles the {@link GetAllUsersQuery} query.
     *
     * @param query the {@link GetAllUsersQuery} query.
     * @return the list of {@link User} entities.
     */
    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}
