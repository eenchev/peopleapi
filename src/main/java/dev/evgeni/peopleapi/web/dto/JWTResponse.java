package dev.evgeni.peopleapi.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JWTResponse {
    private String token;
}
