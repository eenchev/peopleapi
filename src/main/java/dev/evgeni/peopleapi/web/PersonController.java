package dev.evgeni.peopleapi.web;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.evgeni.peopleapi.model.Film;
import dev.evgeni.peopleapi.model.Person;
import dev.evgeni.peopleapi.model.Photo;
import dev.evgeni.peopleapi.repository.FilmRepository;
import dev.evgeni.peopleapi.repository.PersonRepository;
import dev.evgeni.peopleapi.repository.PhotoRepository;
import dev.evgeni.peopleapi.web.dto.CreatePersonRequest;
import dev.evgeni.peopleapi.web.dto.UpdatePersonPhotosRequest;
import dev.evgeni.peopleapi.web.dto.UpdatePersonPhotosResponse;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping(value = "/person")
    private List<Person> getAllPeople() {
        return (List<Person>) personRepository.findAll();
    }

    @GetMapping(value = "/person/{id}")
    private Optional<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @PostMapping(value = "/person")
    private Person createPerson(@RequestBody CreatePersonRequest personRequest) {


        List<Film> films = (List<Film>) filmRepository.findAllById(personRequest.getFilmIds());

        Person person = Person.builder().firstName(personRequest.getFirstName())
                .lastName(personRequest.getLastName()).films(films).build();

        return personRepository.save(person);
    }

    @PutMapping(value = "/person/{id}/photos")
    private UpdatePersonPhotosResponse updatePhotos(@PathVariable Long id,
            @RequestBody UpdatePersonPhotosRequest personPhotos) {

        Person person = personRepository.findById(id).get();

        // for photo of list in request -> fetch foto if it exists in DB -> add photo to list of
        // person's photos

        List<Photo> photosInDb =
                (List<Photo>) photoRepository.findAllById(personPhotos.getPhotoIds());

        person.setPhotos(photosInDb.stream().collect(Collectors.toSet()));

        Person personAfterSave = personRepository.save(person);

        Set<Long> photoIds = personAfterSave.getPhotos().stream().map(p -> p.getId())
                .collect(Collectors.toSet());

        return UpdatePersonPhotosResponse.builder().photoIds(photoIds).build();

    }

    @GetMapping("/person/{id}/photos")
    private UpdatePersonPhotosResponse updatePhotos(@PathVariable Long id) {
        Person person = personRepository.findById(id).get();

        Set<Long> photoIds =
                person.getPhotos().stream().map(p -> p.getId()).collect(Collectors.toSet());

        return UpdatePersonPhotosResponse.builder().photoIds(photoIds).build();


    }

}
