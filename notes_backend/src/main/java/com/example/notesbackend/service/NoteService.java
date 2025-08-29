package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteDtos;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.model.User;
import com.example.notesbackend.repository.NoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for Note CRUD operations.
 */
@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public List<Note> list(User owner) {
        return noteRepository.findAllByOwnerOrderByUpdatedAtDesc(owner);
    }

    // PUBLIC_INTERFACE
    @Transactional
    public Note create(User owner, NoteDtos.NoteRequest req) {
        Note n = new Note();
        n.setTitle(req.title);
        n.setContent(req.content);
        n.setOwner(owner);
        return noteRepository.save(n);
    }

    // PUBLIC_INTERFACE
    @Transactional(readOnly = true)
    public Note get(User owner, Long id) {
        return noteRepository.findByIdAndOwner(id, owner)
                .orElseThrow(() -> new IllegalArgumentException("Note not found"));
    }

    // PUBLIC_INTERFACE
    @Transactional
    public Note update(User owner, Long id, NoteDtos.NoteRequest req) {
        Note existing = get(owner, id);
        existing.setTitle(req.title);
        existing.setContent(req.content);
        return noteRepository.save(existing);
    }

    // PUBLIC_INTERFACE
    @Transactional
    public void delete(User owner, Long id) {
        Note n = get(owner, id); // ensure ownership and existence
        noteRepository.delete(n);
    }
}
