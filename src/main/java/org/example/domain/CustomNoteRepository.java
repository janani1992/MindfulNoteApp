package org.example.domain;

import org.example.model.Note;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;

import java.util.*;

public interface CustomNoteRepository {

    @Query(value = "select * from notes ORDER BY FIELD(priority, 'URGENT', 'HIGH', 'MEDIUM', 'LOW'), created_at DESC",
            nativeQuery = true)
    List<Note> findAllNotesSortedByPriorityCreatedAtDesc();
}
