package dev.evgeni.peopleapi.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.evgeni.peopleapi.constants.Gender;
import dev.evgeni.peopleapi.model.Person;


@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    public List<Person> findAllByGenderOrderByFirstNameAsc(Gender gender);

    public Optional<Person> findByEgn(String egn);

    @Query(nativeQuery = false, value = "SELECT p FROM Person p WHERE p.gender = 1")
    Collection<Person> findAllWomenQuery(Sort sort);


}
