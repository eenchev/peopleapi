package dev.evgeni.peopleapi.web.dto;

import java.util.List;
import dev.evgeni.peopleapi.constants.Gender;
import dev.evgeni.peopleapi.model.Address;
import lombok.Data;

@Data
public class CreatePersonRequest {

    private String firstName;
    private String lastName;
    private Gender gender;

    private List<Long> filmIds;

    private Address address;
}