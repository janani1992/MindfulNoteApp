package org.example.controller;

import org.example.model.Note;
import org.example.model.Priority;
import org.example.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/createNote")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        if (note.getTitle() == null || note.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Note createdNote = noteService.createNote(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdNote);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id).map(ResponseEntity::ok)
                        .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/notes")
    public ResponseEntity<List<Note>> getAllNotes(@RequestParam(defaultValue = "false") boolean sortByPriority) {
        return ResponseEntity.ok(sortByPriority? noteService.getNotesSortedByPriority() : noteService.getAllNotes());
    }

    @PostMapping("/updateNote/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id,
                                          @RequestBody Note noteInfo) {
        if (noteInfo.getTitle() == null || noteInfo.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return noteService.updateNote(noteInfo).map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/deleteNote/{id}")
    public ResponseEntity<Note> deleteNoteById(@PathVariable Long id) {
        if(noteService.deleteNote(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/notes/{priority}")
    public ResponseEntity<List<Note>> getNotesByPriority(@PathVariable Priority priority) {
        return ResponseEntity.ok(noteService.getNotesByPriority(priority));
    }

    @GetMapping("/priority/high")
    public  ResponseEntity<List<Note>> getHighPriorityNotes() {
        return ResponseEntity.ok(noteService.getNotesByPriority(Priority.HIGH));
    }

    @GetMapping("/priority/urgent")
    public ResponseEntity<List<Note>> getUrgentPriorityNotes() {
        return ResponseEntity.ok((noteService.getNotesByPriority(Priority.URGENT)));
    }
}
