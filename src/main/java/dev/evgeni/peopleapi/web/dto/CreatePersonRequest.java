package dev.evgeni.peopleapi.web.dto;

import java.util.List;
import dev.evgeni.peopleapi.model.Address;
import dev.evgeni.peopleapi.validation.Egn;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePersonRequest {

    @NotBlank
    private String firstName;

    private String lastName;
    private String gender;

    @Egn
    private String egn;

    private List<Long> filmIds;

    private Address address;
}
