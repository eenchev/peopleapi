package dev.evgeni.peopleapi.web;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.evgeni.peopleapi.model.Person;
import dev.evgeni.peopleapi.repository.PersonRepository;

@RestController
public class PersonController {


    @Autowired
    private PersonRepository personRepository;

    @GetMapping(value = "/person")
    private List<Person> getAllPeople() {
        return (List<Person>) personRepository.findAll();
    }

    @GetMapping(value = "/person/{id}")
    private Optional<Person> getPersonById(@PathVariable Long id) {
        return personRepository.findById(id);
    }

    @PostMapping(value = "/person")
    private Person createPerson(@RequestBody Person person) {
        return personRepository.save(person);
    }

}
