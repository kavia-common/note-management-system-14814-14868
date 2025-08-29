package com.example.notesbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

/**
 * DTOs for Note operations.
 */
public class NoteDtos {

    public static class NoteRequest {
        @Schema(description = "Note title", example = "Shopping list")
        @NotBlank
        @Size(max = 160)
        public String title;

        @Schema(description = "Note content", example = "Eggs, milk, bread")
        @NotBlank
        public String content;
    }

    public static class NoteResponse {
        public Long id;
        public String title;
        public String content;
        public Instant createdAt;
        public Instant updatedAt;

        public NoteResponse(Long id, String title, String content, Instant createdAt, Instant updatedAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
        }
    }
}
