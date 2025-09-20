package org.example.domain;

import org.example.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long>, CustomNoteRepository{
}
