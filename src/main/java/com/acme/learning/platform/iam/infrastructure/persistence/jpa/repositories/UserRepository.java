package com.acme.learning.platform.iam.infrastructure.persistence.jpa.repositories;

import com.acme.learning.platform.iam.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * This interface represents the repository for the {@link User} aggregate.
 * <p>
 *     This repository is responsible for handling all the persistence operations related to the {@link User} aggregate.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * This method is used to find a user by its username.
     *
     * @param username the user username.
     * @return the {@link User} object.
     */
    Optional<User> findByUsername(String username);

    /**
     * This method is used to check if a user exists by its username.
     *
     * @param username the user username.
     * @return true if the user exists, false otherwise.
     */
    boolean existsByUsername(String username);
}
