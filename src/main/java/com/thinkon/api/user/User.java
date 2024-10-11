package com.thinkon.api.user;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
@Entity(name= "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor

@EqualsAndHashCode(of = "id")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public User(DataCreateUser user) {
        this.username = user.username();
        this.firstName = user.firstName();
        this.lastName = user.lastName();
        this.email = user.email();
        this.phoneNumber = user.phoneNumber();
    }

    public void updateById(@Valid DataUpdateUser user) {

        if(user.firstName() != null) {
            this.firstName = user.firstName();
        }
        if(user.lastName() != null) {
            this.lastName = user.lastName();
        }
        if(user.email() != null) {
            this.email = user.email();
        }
        if(user.phoneNumber() != null) {
            this.phoneNumber = user.phoneNumber();
        }
    }
}
