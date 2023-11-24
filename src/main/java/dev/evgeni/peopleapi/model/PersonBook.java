package dev.evgeni.peopleapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@IdClass(PersonBookPrimaryKey.class)
public class PersonBook {

    @Id
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person personId;

    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book bookId;

    private Integer rating;

    private String review;
}
