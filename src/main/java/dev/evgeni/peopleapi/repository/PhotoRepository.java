package dev.evgeni.peopleapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.evgeni.peopleapi.model.Photo;

@Repository
public interface PhotoRepository extends CrudRepository<Photo, Long> {

}
