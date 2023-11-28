package dev.evgeni.peopleapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.evgeni.peopleapi.model.Film;

@Repository
public interface FilmRepository extends CrudRepository<Film, Long> {

}
