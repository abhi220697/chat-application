package org.example.authserver.service;


import Requests.CreateUserRequest;
import Responses.UserResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.example.authclient.request.RegisterRequest;
import org.example.authclient.response.AuthResponse;
import org.example.authserver.client.UserServiceClient;
import org.example.authserver.data.AuthUser;
import org.example.authserver.repository.AuthRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class AuthService {


    private final AuthRepository  authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final UserServiceClient userServiceClient;


    public AuthService(AuthRepository authRepository, PasswordEncoder passwordEncoder, JwtTokenService jwtTokenService, UserServiceClient userServiceClient) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.userServiceClient = userServiceClient;
    }

    @Transactional
    public AuthResponse registerUser(RegisterRequest request){

        if (authRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }


        UUID userId = UUID.randomUUID();
        AuthUser authUser = new AuthUser();
        authUser.setId(userId);
        authUser.setUsername(request.username());
        authUser.setEmail(request.email());
        authUser.setPassword(
                passwordEncoder.encode(request.password())
        );


        List<String> roles =
                (request.roles() == null || request.roles().isEmpty())
                        ? List.of("INTERNAL_SERVICE")
                        : new ArrayList<>(request.roles());

        authUser.setRoles(new String[]{roles.toString()});

        authRepository.save(authUser);

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setEmail(request.email());
        createUserRequest.setName(request.firstName()+request.lastName());
        createUserRequest.setPhoneNo(request.phone());
        createUserRequest.setAge(25);
        createUserRequest.setPassword(authUser.getPassword());
        ResponseEntity<List<UserResponse>> userResponse = userServiceClient.getAllUsers();
        log.info(userResponse.toString());

        ResponseEntity<UserResponse> response = userServiceClient.createUser(createUserRequest);
        log.info(response.toString());

        String accessToken = jwtTokenService.generateAccessToken(authUser);
        AuthResponse authResponse = AuthResponse.builder()
                .userId(userId)
                .username(authUser.getUsername())
                .accessToken(accessToken)
                .refreshToken(accessToken)
                .roles(roles.toArray(String[]::new))
                .build();

        return authResponse;
    }
}
