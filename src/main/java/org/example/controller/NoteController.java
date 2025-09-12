package org.example.controller;

import org.example.model.Note;
import org.example.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/notes/{id}")
    public ResponseEntity<?> getNote(@PathVariable String id) {
        Optional<Note> note = noteService.getNote(id);
        return ResponseEntity.ok(note);
    }


    @PostMapping("/updateNote")
    public ResponseEntity<?> updateNote(@RequestBody Note note) {
        if (note.name == null || note.name.trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Note name cannot be null or empty");
        }

        if (note.getName() == null || note.getName().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Note name cannot be null or empty");
        }


        try {
            Optional<Note> updatedNote = noteService.updateNote(note);
            return ResponseEntity.ok(updatedNote);
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Error creating note: " + e.getMessage());
        }
    }

}
