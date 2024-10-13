package com.thinkon.api.dto;

import com.thinkon.api.domain.model.User;
/**
 * A DTO for listing user details.
 *
 * @param id The user's ID.
 * @param username The user's username.
 * @param firstName The user's first name.
 * @param lastName The user's last name.
 * @param email The user's email address.
 * @param phoneNumber The user's phone number.
 */
public record DataListUsers(Long id, String username, String firstName, String lastName, String email, String phoneNumber) {
    /**
     * Constructs a {@code DataListUsers} record from a {@link User} entity.
     *
     * @param user The {@link User} entity to extract data from.
     */
    public DataListUsers(User user){
        this(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }
}
