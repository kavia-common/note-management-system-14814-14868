package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.model.User;
import com.example.notesbackend.service.NoteService;
import com.example.notesbackend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST endpoints for Note CRUD operations.
 */
@RestController
@RequestMapping("/api/notes")
@Tag(name = "Notes", description = "CRUD operations on notes")
public class NoteController {

    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    private User currentUser(Authentication auth) {
        return userService.getDomainUser(auth.getName());
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List notes", description = "Returns all notes for the authenticated user")
    public List<NoteDtos.NoteResponse> list(Authentication auth) {
        User owner = currentUser(auth);
        return noteService.list(owner).stream()
                .map(this::toDto)
                .toList();
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(summary = "Create note", description = "Create a new note")
    public NoteDtos.NoteResponse create(Authentication auth, @Valid @RequestBody NoteDtos.NoteRequest request) {
        User owner = currentUser(auth);
        Note n = noteService.create(owner, request);
        return toDto(n);
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get note", description = "Retrieve a note by ID")
    public NoteDtos.NoteResponse get(Authentication auth, @PathVariable Long id) {
        User owner = currentUser(auth);
        return toDto(noteService.get(owner, id));
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(summary = "Update note", description = "Update a note by ID")
    public NoteDtos.NoteResponse update(Authentication auth,
                                        @PathVariable Long id,
                                        @Valid @RequestBody NoteDtos.NoteRequest request) {
        User owner = currentUser(auth);
        return toDto(noteService.update(owner, id, request));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note", description = "Delete a note by ID")
    public ResponseEntity<?> delete(Authentication auth, @PathVariable Long id) {
        User owner = currentUser(auth);
        noteService.delete(owner, id);
        return ResponseEntity.noContent().build();
    }

    private NoteDtos.NoteResponse toDto(Note n) {
        return new NoteDtos.NoteResponse(n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(), n.getUpdatedAt());
    }
}
