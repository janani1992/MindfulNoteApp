package org.example.controller;

import org.example.model.Note;
import org.example.services.NoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class NoteControllerTest {

    @Mock
    private NoteService noteService;

    @InjectMocks
    private NoteController noteController;

    private ObjectMapper objectMapper;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    void createNote_ShouldReturnCreatedNote() throws Exception {
        Note newNote = new Note("test");
        Note savedNote = new Note(3L, "test");

        when(noteService.createNote(any(Note.class))).thenReturn(savedNote);

        mockMvc.perform(post("/api/createNote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newNote)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(3)))
                .andExpect(jsonPath("$.name", is("test")));
    }

    @Test
    void createNote_ShouldNotReturnNullNote() throws Exception {

        when(noteService.createNote(any(Note.class))).thenThrow(new IllegalArgumentException("Note cannot be null"));

        mockMvc.perform(post("/api/createNote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("null"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createNote_ShouldNotReturnNullName() throws Exception {
        Note newNote = new Note(null);
        String expectedErrorJson = "{\"error\": \"Note name cannot be null or empty\"}";
        when(noteService.createNote(any(Note.class))).thenThrow(new IllegalArgumentException("Note name cannot be null or empty"));

        mockMvc.perform(post("/api/createNote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newNote)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("Note name cannot be null or empty"))); // <-- This line causes the error    }
    }
}