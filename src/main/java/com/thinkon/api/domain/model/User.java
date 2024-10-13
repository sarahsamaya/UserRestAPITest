package com.thinkon.api.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

/**
 * Represents a user entity in the system.
 *
 * Contains basic user information such as username, first name, last name, email, and phone number.
 * This entity is mapped to the "users" table with unique constraints on username and email.
 */
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Entity(name= "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@EqualsAndHashCode(of = "id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;

}
