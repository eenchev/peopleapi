package dev.evgeni.peopleapi.mapper;

import org.mapstruct.Mapper;
import dev.evgeni.peopleapi.model.Address;
import dev.evgeni.peopleapi.web.dto.AddressDto;

@Mapper
public interface AddressMapper {

    Address addressFromDto(AddressDto addressDto);

}
