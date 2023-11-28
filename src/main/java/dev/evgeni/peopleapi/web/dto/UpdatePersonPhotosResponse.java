package dev.evgeni.peopleapi.web.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePersonPhotosResponse {

    private Set<Long> photoIds;

}
