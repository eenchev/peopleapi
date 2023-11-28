package dev.evgeni.peopleapi.model;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    @Setter(value = AccessLevel.NONE)
    private Long id;

    private String name;
    private String author;

    @OneToMany(mappedBy = "bookId")
    private List<PersonBook> personBookRelation;
}
