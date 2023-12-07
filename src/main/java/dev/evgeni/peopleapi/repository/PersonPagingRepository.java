package dev.evgeni.peopleapi.repository;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import dev.evgeni.peopleapi.model.Person;

@Repository
public interface PersonPagingRepository extends PagingAndSortingRepository<Person, Long> {

    List<Person> findAllBy(Pageable pageable);

}
