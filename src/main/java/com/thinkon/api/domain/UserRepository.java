package com.thinkon.api.domain;
import com.thinkon.api.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link User} entities.
 *
 * Extends {@link JpaRepository} to provide CRUD operations on the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {}

