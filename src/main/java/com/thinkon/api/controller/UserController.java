package com.thinkon.api.controller;

import com.thinkon.api.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public void createUser(@RequestBody @Valid DataCreateUser user){
        if (userRepository.existsByUsername(user.username())) {
            throw new IllegalArgumentException("Username already in use");
        }
        if (userRepository.existsByEmail(user.email())) {
            throw new IllegalArgumentException("Email already in use");
        }
        userRepository.save(new User(user));
    }

    @GetMapping
    public Page<DataListUsers> listUsers(@PageableDefault(size=15, sort ={"firstName"}) Pageable pagination) {
        return userRepository.findAll(pagination).map(DataListUsers::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUsersById(@PathVariable Long id) {
        var aUser = userRepository.findById(id);
        return aUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public void updateUser(@RequestBody @Valid DataUpdateUser user){
        var aUser = userRepository.getReferenceById(user.id());
        aUser.updateById(user);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleteUser(@PathVariable Long id){
        userRepository.deleteById(id);
    }
}
