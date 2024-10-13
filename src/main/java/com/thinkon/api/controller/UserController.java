package com.thinkon.api.controller;

import com.thinkon.api.domain.model.User;
import com.thinkon.api.dto.DataCreateUser;
import com.thinkon.api.dto.DataListUsers;
import com.thinkon.api.dto.DataUpdateUser;
import com.thinkon.api.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserService userService;

    /**
     * Creates a new user.
     *
     * @param user Data for creating the user.
     * @return The created user's ID and HTTP status:
     *         - 201 (CREATED) on success,
     *         - 400 (BAD_REQUEST) for validation errors,
     *         - 500 (INTERNAL_SERVER_ERROR) for other errors.
     */
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody DataCreateUser user) {
        try {
            User createdUser = userService.createUser(user);

            return new ResponseEntity<>(createdUser.getId().toString(), HttpStatus.CREATED);
        } catch (Exception e) {
            if(e.getClass().equals(ValidationException.class)) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates an existing user.
     *
     * @param user Data for updating the user.
     * @return The updated user and HTTP status:
     *         - 200 (OK) on success,
     *         - 400 (BAD_REQUEST) for validation errors,
     *         - 404 (NOT_FOUND) if the user doesn't exist,
     *         - 500 (INTERNAL_SERVER_ERROR) for other errors.
     */
    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid DataUpdateUser user) {
        try {
        // Call the service to update the user
        User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (Exception e) {
            if(e.getClass().equals(ValidationException.class)) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            if(e.getMessage().equals("User already exists")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieves a paginated list of users.
     *
     * @param pagination Pagination settings (page, size, sorting).
     * @return A page of users.
     */
    @GetMapping
    public Page<DataListUsers> listUsers(@PageableDefault(size = 15, sort = {"firstName"}) Pageable pagination) {
            return userService.listUsers(pagination);
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user details and HTTP status:
     *         - 302 (FOUND) if the user exists,
     *         - 404 (NOT_FOUND) if the user is not found,
     *         - 500 (INTERNAL_SERVER_ERROR) for other errors.
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> detailUserById(@PathVariable Long id) {

        try {
            User user = userService.detailUserById(id);
            return new ResponseEntity<>(user, HttpStatus.FOUND);

        } catch (Exception e) {
            if(e.getMessage().equals("User not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Deletes a user by their ID.
     *
     * @param id The ID of the user to delete.
     * @return A response indicating:
     *         - 200 (OK) if the deletion was successful,
     *         - 404 (NOT_FOUND) if the user was not found,
     *         - 500 (INTERNAL_SERVER_ERROR) for other errors.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        //try catch
        try {
            boolean thisReturn = userService.deleteUser(id);

            return new ResponseEntity<>( thisReturn, HttpStatus.OK);

        } catch (Exception e) {
            if(e.getMessage().equals("User not found")) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}