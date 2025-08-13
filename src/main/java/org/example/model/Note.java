package org.example.model;


import jakarta.persistence.*;

@Entity
@Table(name = "note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    public String name;

    public Note(String name) {
        this.name = name;
    }

}
