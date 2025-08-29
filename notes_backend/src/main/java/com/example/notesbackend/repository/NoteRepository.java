package com.example.notesbackend.repository;

import com.example.notesbackend.model.Note;
import com.example.notesbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository for Note entity.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByOwnerOrderByUpdatedAtDesc(User owner);
    Optional<Note> findByIdAndOwner(Long id, User owner);
    void deleteByIdAndOwner(Long id, User owner);
}
