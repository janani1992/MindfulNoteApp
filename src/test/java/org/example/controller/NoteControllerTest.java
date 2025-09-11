package org.example.controller;

import org.example.model.Note;
import org.example.services.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(NoteController.class)
public class NoteControllerTest{


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private NoteService noteService;

    @Autowired
    private ObjectMapper objectMapper;

    private Note testNote;
    private List<Note> noteList;


    @Test
    void createUser_ShouldReturnCreatedUser() throws Exception {
        Note newNote = new Note("my first note");
        Note savedNote = new Note(1L, "my first note");
        when(noteService.createNote(any(Note.class))).thenReturn(savedNote);

        String jsonContent = objectMapper.writeValueAsString(newNote);

        mockMvc.perform(post("/api/createNote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("Bob Wilson")))
                .andExpect(jsonPath("$.email", is("bob@example.com")));
    }

}
