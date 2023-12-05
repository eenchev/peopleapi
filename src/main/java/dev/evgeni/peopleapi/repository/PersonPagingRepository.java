package dev.evgeni.peopleapi.repository;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import dev.evgeni.peopleapi.model.Person;

@Repository
public interface PersonPagingRepository extends ListPagingAndSortingRepository<Person, Long> {

}
