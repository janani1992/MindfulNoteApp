package org.example.services;

import org.example.domain.NoteRepository;
import org.example.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Transactional
    public Note createNote(Note note) {
        if (note == null) {
            throw new IllegalArgumentException("Note cannot be null");
        }

        if (note.getName() == null || note.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Note name cannot be null or empty");
        }

        // Check for duplicate names if needed (this is likely where line 18 was causing issues)
        // Instead of: if (note.name.equals(existingName)) - which causes NPE
        // Use safe comparison:
        List<Note> existingNotes = noteRepository.findAll();
        for (Note existingNote : existingNotes) {
            if (Objects.equals(note.getName(), existingNote.getName())) {
                throw new IllegalArgumentException("A note with this name already exists");
            }
        }

        // Trim the name to remove leading/trailing spaces
        note.setName(note.getName().trim());

        return noteRepository.save(note);
    }
}
