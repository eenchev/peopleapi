package dev.evgeni.peopleapi.web.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UpdatePersonPhotosRequest {

    private Set<Long> photoIds;

}
