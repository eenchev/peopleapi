package dev.evgeni.peopleapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Photo {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @Lob
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
