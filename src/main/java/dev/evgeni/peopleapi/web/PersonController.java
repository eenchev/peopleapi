package dev.evgeni.peopleapi.web;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.evgeni.peopleapi.error.NotFoundObjectException;
import dev.evgeni.peopleapi.mapper.PersonMapper;
import dev.evgeni.peopleapi.model.Film;
import dev.evgeni.peopleapi.model.Person;
import dev.evgeni.peopleapi.model.Photo;
import dev.evgeni.peopleapi.repository.FilmRepository;
import dev.evgeni.peopleapi.repository.PersonPagingRepository;
import dev.evgeni.peopleapi.repository.PersonRepository;
import dev.evgeni.peopleapi.repository.PhotoRepository;
import dev.evgeni.peopleapi.service.ObjectValidator;
import dev.evgeni.peopleapi.web.dto.CreatePersonRequest;
import dev.evgeni.peopleapi.web.dto.PersonApiPage;
import dev.evgeni.peopleapi.web.dto.UpdatePersonPhotosRequest;
import dev.evgeni.peopleapi.web.dto.UpdatePersonPhotosResponse;
import dev.evgeni.peopleapi.web.dto.UpdatePersonRequest;
import jakarta.validation.constraints.Min;

@RestController
public class PersonController {

    private static final Integer PAGE_SIZE = 10;

    @Autowired
    private ObjectValidator validator;

    @Autowired
    public PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonPagingRepository personPagingRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping(value = "/person")
    @Validated
    private PersonApiPage<Person> getAllPeople(
            @RequestParam(required = false, defaultValue = "0") @Min(0) Integer page) {
        PageRequest pageRequest = PageRequest.of(page, PAGE_SIZE);
        // return new PersonApiPage<>(personPagingRepository.findAllBy(pageRequest), pageRequest);
        return new PersonApiPage<>(personPagingRepository.findAll(pageRequest));
    }

    @GetMapping(value = "/person/{id}")
    private Person getPersonById(@PathVariable Long id) {
        return personRepository.findById(id).orElseThrow(() -> {
            throw new NotFoundObjectException(Person.class.getName(), String.valueOf(id));
        });
    }

    @PostMapping(value = "/person")
    private ResponseEntity<Person> createPerson(@RequestBody CreatePersonRequest personRequest) {

        validator.validate(personRequest);

        Person person = personMapper.personFromCreateRequest(personRequest);

        if (personRequest.getFilmIds() != null) {
            List<Film> films = (List<Film>) filmRepository.findAllById(personRequest.getFilmIds());
            person.setFilms(films);
        }

        return ResponseEntity.status(201).body(personRepository.save(person));
    }

    @PatchMapping(value = "/person/{id}")
    private Person updatePerson(@PathVariable Long id,
            @RequestBody UpdatePersonRequest personRequest) {

        Person person = personRepository.findById(id).get();

        personMapper.updatePersonFromUpdateRequest(personRequest, person);

        if (personRequest.getFilmIds() != null) {
            List<Film> films = (List<Film>) filmRepository.findAllById(personRequest.getFilmIds());
            person.setFilms(films);
        }

        return personRepository.save(person);
    }

    @PutMapping(value = "/person/{id}/photos")
    private UpdatePersonPhotosResponse updatePhotos(@PathVariable Long id,
            @RequestBody UpdatePersonPhotosRequest personPhotos) {

        Person person = personRepository.findById(id).get();

        List<Photo> photosInDb =
                (List<Photo>) photoRepository.findAllById(personPhotos.getPhotoIds());

        person.setPhotos((photosInDb.stream().collect(Collectors.toSet())));

        Set<Long> photoIds = personRepository.save(person).getPhotos().stream().map(p -> p.getId())
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

    @DeleteMapping("/person/{id}")
    private void deletePerson(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

}
