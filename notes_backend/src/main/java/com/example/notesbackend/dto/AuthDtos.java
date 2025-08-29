package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

/**
 * DTOs for authentication endpoints.
 */
public class AuthDtos {

    public static class SignUpRequest {
        @Schema(description = "Desired username", example = "alice")
        @NotBlank
        public String username;

        @Schema(description = "Password", example = "P@ssw0rd!")
        @NotBlank
        public String password;
    }

    public static class LoginRequest {
        @Schema(description = "Username", example = "alice")
        @NotBlank
        public String username;

        @Schema(description = "Password", example = "P@ssw0rd!")
        @NotBlank
        public String password;
    }

    public static class AuthResponse {
        @Schema(description = "JWT access token")
        public String token;
        @Schema(description = "Logged in username")
        public String username;

        public AuthResponse(String token, String username) {
            this.token = token;
            this.username = username;
        }
    }
}
