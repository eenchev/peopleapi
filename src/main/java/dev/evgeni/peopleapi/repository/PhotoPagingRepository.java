package dev.evgeni.peopleapi.repository;

import org.springframework.data.repository.ListPagingAndSortingRepository;
import dev.evgeni.peopleapi.model.Photo;

public interface PhotoPagingRepository extends ListPagingAndSortingRepository<Photo, Long> {

}
