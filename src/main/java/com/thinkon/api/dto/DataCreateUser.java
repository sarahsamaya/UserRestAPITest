package com.thinkon.api.dto;

import jakarta.validation.constraints.*;

/**
 * A DTO for create user.
 *
 * @param username The user's username.
 * @param firstName The user's first name.
 * @param lastName The user's last name.
 * @param email The user's email address.
 * @param phoneNumber The user's phone number.
 */
public record DataCreateUser(
        @NotNull
        @NotBlank
        String username,
        @NotNull
        @NotBlank
        String firstName,
        @NotNull
        @NotBlank
        String lastName,
        @NotNull
        @Email
        String email,
        @NotNull
        @NotBlank
        String phoneNumber
){}
