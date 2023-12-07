package dev.evgeni.peopleapi.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressDto {

    private String street;
    private Integer streetNo;

}
