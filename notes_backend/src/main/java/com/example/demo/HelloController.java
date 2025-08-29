package com.example.notesbackend;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Basic endpoints and docs redirects.
 */
@RestController
@Tag(name = "Root", description = "Basic endpoints for service status and docs")
public class HelloController {

    // PUBLIC_INTERFACE
    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Welcome endpoint", description = "Returns a welcome message")
    public String hello() {
        return "Notes Backend Service is running";
    }

    // PUBLIC_INTERFACE
    @GetMapping("/docs")
    @Operation(summary = "API Documentation", description = "Redirects to Swagger UI")
    public RedirectView docs() {
        return new RedirectView("/swagger-ui.html");
    }

    // PUBLIC_INTERFACE
    @GetMapping(value = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Health check", description = "Returns application health status")
    public String health() {
        return "OK";
    }

    // PUBLIC_INTERFACE
    @GetMapping(value = "/api/info", produces = MediaType.TEXT_PLAIN_VALUE)
    @Operation(summary = "Application info", description = "Returns application information")
    public String info() {
        return "Spring Boot Application: notesbackend";
    }
}