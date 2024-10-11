package com.thinkon.api.user;

public record DataListUsers(Long id, String username, String firstName, String lastName, String email, String phoneNumber) {

    public DataListUsers(User user){
        this(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }
}
