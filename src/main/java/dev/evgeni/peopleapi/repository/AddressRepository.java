package dev.evgeni.peopleapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import dev.evgeni.peopleapi.model.Address;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long> {

}
