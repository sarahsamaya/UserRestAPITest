package com.thinkon.api.service;

import com.thinkon.api.domain.*;
import com.thinkon.api.domain.model.User;
import com.thinkon.api.dto.DataCreateUser;
import com.thinkon.api.dto.DataListUsers;
import com.thinkon.api.dto.DataUpdateUser;
import com.thinkon.api.validator.UserValidator;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserValidator userValidator;

    /**
     * Creates a new user.
     *
     * @param createUser Data for creating the user.
     * @return The created user.
     */
    public User createUser(DataCreateUser createUser) {
            var user = new User(null, createUser.username(), createUser.firstName(), createUser.lastName(), createUser.email(), createUser.phoneNumber());

            userValidator.validateUserData(user);

            userRepository.save(user);
            return user;
    }

    /**
     * Updates an existing user.
     *
     * @param updateUser Data for updating the user.
     * @return The updated user.
     * @throws ValidationException if the user is not found.
     */
    public User updateUser(DataUpdateUser updateUser) {
        // Fetch the existing user by ID (the one being updated)
        var existingUser = userRepository.findById(updateUser.id()).orElse(null);
        if (existingUser == null) {
            throw new ValidationException("User not found");
        }

        // Update fields (keep the existing username)
        var user = new User(existingUser.getId(), updateUser.username(), updateUser.firstName(), updateUser.lastName(), updateUser.email(), updateUser.phoneNumber());

        userValidator.validateUserData(user);

        // Save the updated user
        userRepository.save(user);
        return user;
    }

    /**
     * Retrieves a paginated list of users.
     *
     * @param pagination Pagination settings.
     * @return A page of users.
     */
    public Page<DataListUsers> listUsers(Pageable pagination) {
        return userRepository.findAll(pagination).map(DataListUsers::new);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user.
     * @throws ValidationException if the user is not found.
     */
    public User detailUserById(Long id) {
        var findUser = userRepository.findById(id).orElse(null);
        if (findUser == null) {
            throw new ValidationException("User not found");
        }
        return findUser;
    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return {@code true} if the user was deleted.
     * @throws ValidationException if the user is not found.
     */
        public Boolean deleteUser(Long id) {
            var deleteUser = userRepository.findById(id).orElse(null);

            if (deleteUser == null) {
                throw new ValidationException("User not found");
            }
            userRepository.delete(deleteUser);

            return true;
        }


}

