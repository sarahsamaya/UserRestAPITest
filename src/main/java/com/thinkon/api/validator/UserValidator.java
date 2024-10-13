package com.thinkon.api.validator;

import com.thinkon.api.domain.model.User;
import com.thinkon.api.domain.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Component;
import jakarta.validation.ValidationException;

/**
 * Validates the user data to ensure all required fields are present and valid.
 */
@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Validates the user data.
     *
     * @param user The user data to validate.
     * @throws ValidationException if any required field is missing or invalid.
     */
    public void validateUserData(@Valid User user) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            throw new ValidationException("Username Name is required");
        }
        if (user.getFirstName() == null || user.getFirstName().isBlank()) {
            throw new ValidationException("First Name is required");
        }
        if (user.getLastName() == null || user.getLastName().isBlank()) {
            throw new ValidationException("Last Name is required");
        }
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ValidationException("Email is required");
        }

        if (user.getPhoneNumber() == null || user.getPhoneNumber().isBlank()) {
            throw new ValidationException("Phone Number is required");
        }
    }


}
