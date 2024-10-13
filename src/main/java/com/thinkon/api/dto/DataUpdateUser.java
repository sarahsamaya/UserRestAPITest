        package com.thinkon.api.dto;

        import jakarta.validation.constraints.Email;
        import jakarta.validation.constraints.NotBlank;
        import jakarta.validation.constraints.NotNull;

        /**
         * A DTO for update user.
         * @param id The user's ID.
         * @param username The user's username.
         * @param firstName The user's first name.
         * @param lastName The user's last name.
         * @param email The user's email address.
         * @param phoneNumber The user's phone number.
         */
        public record DataUpdateUser(
                @NotNull
                Long id,
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
        ) {



            }

