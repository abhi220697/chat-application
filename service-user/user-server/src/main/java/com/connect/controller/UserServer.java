package com.connect.controller;


import Requests.CreateUserRequest;
import Requests.UpdateUserRequest;
import Responses.UserResponse;
import com.connect.service.UserManager;
import controller.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


@RestController
@Slf4j
public class UserServer implements UserApi {

    private  final UserManager userManager;

    public UserServer(UserManager userManager){
        this.userManager = userManager;
    }

    @Override
    @PreAuthorize("hasRole('INTERNAL_SERVICE')")
    public ResponseEntity<UserResponse> createUser(CreateUserRequest request) {
        log.info("Received request to create user: {}", request);
        UserResponse createdUser = userManager.createUser(request);
        // 201 CREATED is appropriate for create
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @Override
    public ResponseEntity<UserResponse> getUserById(UUID id) {
        log.info("Received request to get user by id: {}", id);
        UserResponse user = userManager.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        log.info("Received request to get all users");
        List<UserResponse> users = userManager.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UUID id, UpdateUserRequest request) {
        log.info("Received request to update user with id: {} and body: {}", id, request);
        UserResponse updatedUser = userManager.updateUser(id, request);
        return ResponseEntity.ok(updatedUser);
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id) {
        log.info("Received request to delete user with id: {}", id);
        userManager.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
