package org.example.controller;

import org.example.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/createNote")
    public ResponseEntity<String> createNote(String name) {
        return ResponseEntity.ok(noteService.createNote(name));
    }

}
