package org.example.authserver.controller;

import org.example.authclient.AuthApi;
import org.example.authclient.request.LoginRequest;
import org.example.authclient.request.RefreshTokenRequest;
import org.example.authclient.request.RegisterRequest;
import org.example.authclient.request.ValidateTokenRequest;
import org.example.authclient.response.AuthResponse;
import org.example.authserver.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthApi {
    private static final Logger log =
            LoggerFactory.getLogger(AuthService.class);

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @Override
    public ResponseEntity<AuthResponse> register(RegisterRequest request) {
        log.info("inside the request");
        ResponseEntity<AuthResponse> authResponseResponseEntity ;
        AuthResponse authResponse = authService.registerUser(request);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> refreshToken(RefreshTokenRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> validateToken(ValidateTokenRequest request) {
        return null;
    }

    @Override
    public ResponseEntity<Void> logout() {
        return null;
    }

    @Override
    public ResponseEntity<AuthResponse> getCurrentUser() {
        return null;
    }

    @Override
    public ResponseEntity<Void> forgotPassword(String email) {
        return null;
    }

    @Override
    public ResponseEntity<Void> resetPassword(String token, String newPassword) {
        return null;
    }
}
