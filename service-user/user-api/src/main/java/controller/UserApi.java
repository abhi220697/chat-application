package controller;

import Requests.CreateUserRequest;
import Requests.UpdateUserRequest;
import Responses.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



public interface UserApi {
    @Operation(
            summary = "Create a new user",
            description = "Creates a new user with the provided details"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PostMapping("/api/v1/user")
    ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request
    );

    @Operation(
            summary = "Get user by ID",
            description = "Returns user details for the given user ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/users/{id}")
    ResponseEntity<UserResponse> getUserById(@PathVariable("id") UUID id);


    @Operation(
            summary = "Get all users",
            description = "Returns a list of all users"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users")
    })
    @GetMapping("/api/v1/user")
    ResponseEntity<List<UserResponse>> getAllUsers();

    @Operation(
            summary = "Update an existing user",
            description = "Updates the user details for the given user ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/api/v1/user/{id}")
    ResponseEntity<UserResponse> updateUser(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UpdateUserRequest request
    );

    @Operation(
            summary = "Delete a user",
            description = "Deletes the user with the given ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "User deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/api/v1/user/{id}")
    ResponseEntity<Void> deleteUser(
            @PathVariable("id") UUID id
    );
}

