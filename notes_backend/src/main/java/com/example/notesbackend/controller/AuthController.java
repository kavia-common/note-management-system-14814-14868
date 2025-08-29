package com.example.notesbackend.controller;

import com.example.notesbackend.dto.AuthDtos;
import com.example.notesbackend.security.JwtService;
import com.example.notesbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * Authentication endpoints for signup and login.
 */
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "User signup and login")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authManager;
    private final JwtService jwtService;

    public AuthController(UserService userService,
                          AuthenticationManager authManager,
                          JwtService jwtService) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtService = jwtService;
    }

    // PUBLIC_INTERFACE
    @PostMapping("/signup")
    @Operation(summary = "User signup", description = "Register a new user account")
    public ResponseEntity<?> signup(@Valid @RequestBody AuthDtos.SignUpRequest request) {
        try {
            var user = userService.register(request.username, request.password);
            var token = jwtService.generateToken(user.getUsername(), java.util.Map.of("roles", user.getRoles()));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new AuthDtos.AuthResponse(token, user.getUsername()));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(java.util.Map.of("error", ex.getMessage()));
        }
    }

    // PUBLIC_INTERFACE
    @PostMapping("/login")
    @Operation(summary = "User login", description = "Authenticate and receive a JWT token")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDtos.LoginRequest request) {
        try {
            var auth = new UsernamePasswordAuthenticationToken(request.username, request.password);
            authManager.authenticate(auth);
            var token = jwtService.generateToken(request.username, java.util.Map.of());
            return ResponseEntity.ok(new AuthDtos.AuthResponse(token, request.username));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(java.util.Map.of("error", "Invalid credentials"));
        }
    }
}
