package dev.evgeni.peopleapi.web.dto;

import java.util.List;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonRequest {

    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String gender;

    private List<Long> filmIds;

    private AddressDto address;

}
