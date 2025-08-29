package com.example.notesbackend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application entry point for the Notes Backend.
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Notes Backend API",
                version = "0.1.0",
                description = "RESTful APIs for note management and user authentication",
                contact = @Contact(name = "Notes API Team", email = "support@example.com")
        ),
        servers = {@Server(url = "/", description = "Default Server")}
)
public class notesbackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(notesbackendApplication.class, args);
    }
}
