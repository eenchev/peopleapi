package dev.evgeni.peopleapi.error;

import java.util.UUID;
import lombok.Getter;

@Getter
public class PersonApiException extends RuntimeException {

    private UUID id;

    PersonApiException(String message) {
        super(message);
        this.id = UUID.randomUUID();
    }

}
