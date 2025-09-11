package org.example.controller;

import org.example.model.Note;
import org.example.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/createNote")
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        if (note.name == null || note.name.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Note name cannot be null or empty");
        }

        if (note.getName() == null || note.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Note name cannot be null or empty");
        }


        try {
            Note createdNote = noteService.createNote(note);
            return ResponseEntity.ok(createdNote);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error creating note: " + e.getMessage());
        }
    }

}
