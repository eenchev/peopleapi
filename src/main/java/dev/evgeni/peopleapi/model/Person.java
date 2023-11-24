package dev.evgeni.peopleapi.model;

import java.util.List;
import java.util.Set;
import dev.evgeni.peopleapi.constants.Gender;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = true)
    private Address address;

    @OneToMany(mappedBy = "person")
    private Set<Photo> photos;

    @ManyToMany(targetEntity = Film.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Film> films;

    private Gender gender;

    @OneToMany(mappedBy = "personId")
    private List<PersonBook> personBookRelation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
