package com.thinkon.api.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record DataUpdateUser(
        @NotNull
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber) {


    }

