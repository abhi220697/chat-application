package com.connect.service;

import Requests.CreateUserRequest;
import Requests.UpdateUserRequest;
import Responses.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserManager {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getUserById(UUID id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(UUID id, UpdateUserRequest request);

    void deleteUser(UUID id);
}
