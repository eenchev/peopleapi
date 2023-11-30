package dev.evgeni.peopleapi.web.dto;

import java.util.List;
import dev.evgeni.peopleapi.constants.Gender;
import dev.evgeni.peopleapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonRequest {

    private String firstName;
    private String lastName;
    private Gender gender;

    private List<Long> filmIds;

    private Address address;

}
