package dev.evgeni.peopleapi.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;
import dev.evgeni.peopleapi.model.Person;

@DataJpaTest
// @Profile("unit")
public class PersonRepositoryTest {

    private static final Long PERSON_ID = 15L;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @Sql("classpath:db/person15.sql")
    void shouldFetchPersonByIdFromDb() {
        Optional<Person> person = repository.findById(PERSON_ID);
        assertTrue(person.isPresent());
    }

    @Test
    void shouldExistPersonByEgnNumber() {
        String egnNumber = "7777777777";
        Person p = Person.builder().firstName("Pesho").egn(egnNumber).address(null).build();

        em.persist(p);

        assertTrue(repository.findByEgn(egnNumber).isPresent());
        assertTrue(repository.findByEgn("4543432432").isEmpty());
    }

}
