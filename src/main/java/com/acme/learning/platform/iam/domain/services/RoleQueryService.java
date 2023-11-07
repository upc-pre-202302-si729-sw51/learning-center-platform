package com.acme.learning.platform.iam.domain.services;

import com.acme.learning.platform.iam.domain.model.entities.Role;
import com.acme.learning.platform.iam.domain.model.queries.GetAllRolesQuery;

import java.util.List;

/**
 * This interface represents the query service for the {@link Role} entity.
 *
 */
public interface RoleQueryService {
    /**
     * This method handles the {@link GetAllRolesQuery} query.
     *
     * @param query the {@link GetAllRolesQuery} query.
     * @return the list of {@link Role} entities.
     */
    List<Role> handle(GetAllRolesQuery query);
}
