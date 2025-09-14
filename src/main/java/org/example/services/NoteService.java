package org.example.services;

import org.example.domain.NoteRepository;
import org.example.model.Note;
import org.example.model.Priority;
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

        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Note name cannot be null or empty");
        }


        List<Note> existingNotes = noteRepository.findAll();
        for (Note existingNote : existingNotes) {
            if (Objects.equals(note.getTitle(), existingNote.getTitle())) {
                throw new IllegalArgumentException("A note with this name already exists");
            }
        }

        note.setTitle(note.getTitle().trim());

        return noteRepository.save(note);
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Optional<Note> updateNote(Note note) {
        Optional<Note> optionalNote = noteRepository.findById(note.getId());

        if(optionalNote.isPresent()) {
            Note existingNote = optionalNote.get();
            existingNote.setTitle(note.getTitle());

            return Optional.of(noteRepository.save(existingNote));
        }

        return Optional.empty();
    }

    public boolean deleteNote(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if(note.isPresent()){
            noteRepository.delete(note.get());
            return true;
        }
        return false;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }


    public List<Note> getNotesSortedByPriority() {
        return noteRepository.findAllNotesSortedByPriorityCreatedAtDesc();
    }

    public List<Note> getNotesByPriority(Priority priority) {
        return noteRepository.findAll()
                .stream()
                .filter(note -> note.getPriority() == priority)
                .toList();
    }
}
