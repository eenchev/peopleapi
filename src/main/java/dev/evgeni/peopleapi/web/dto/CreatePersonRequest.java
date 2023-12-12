package dev.evgeni.peopleapi.web.dto;

import java.util.List;
import dev.evgeni.peopleapi.validation.Egn;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatePersonRequest {

    @NotBlank
    private String firstName;

    private String lastName;
    private String gender;

    @Egn
    private String egn;

    private List<Long> filmIds;

    private String street;
    private Integer streetNo;
}
