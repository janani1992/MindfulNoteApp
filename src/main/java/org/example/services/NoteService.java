package org.example.services;

import org.example.domain.NoteRepository;
import org.example.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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


        List<Note> existingNotes = noteRepository.findAll();
        for (Note existingNote : existingNotes) {
            if (Objects.equals(note.getName(), existingNote.getName())) {
                throw new IllegalArgumentException("A note with this name already exists");
            }
        }

        note.setName(note.getName().trim());

        return noteRepository.save(note);
    }

    public Optional<Note> getNote(String id) {
        return noteRepository.findById(Long.valueOf(id));
    }

    public Optional<Note> updateNote(Note note) {
        Optional<Note> optionalNote = noteRepository.findById(note.getId());

        if(optionalNote.isPresent()) {
            Note existingNote = optionalNote.get();
            existingNote.setName(note.getName());

            return Optional.of(noteRepository.save(existingNote));
        }

        return Optional.empty();
    }
}
