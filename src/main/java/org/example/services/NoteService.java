package org.example.services;

import org.example.domain.NoteRepository;
import org.example.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Transactional
    public String createNote(String name) {
        Note note = noteRepository.save(new Note(name));
        return note.name.equals(name)? "Success "+note.name : "Failure";
    }
}
