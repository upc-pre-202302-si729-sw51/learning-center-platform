package com.acme.learning.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.learning.platform.iam.domain.model.entities.Role;
import com.acme.learning.platform.iam.domain.model.valueobjects.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the repository for the {@link Role} entity.
 * <p>
 *     This repository is responsible for handling all the persistence operations related to the {@link Role} entity.
 * </p>
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    /**
     * This method is used to find a role by its name.
     *
     * @param name the role name.
     * @return the {@link Role} entity.
     */
    Optional<Role> findByName(Roles name);

    /**
     * This method is used to check if a role exists by its name.
     *
     * @param name the role name.
     * @return true if the role exists, false otherwise.
     */
    boolean existsByName(Roles name);
}
