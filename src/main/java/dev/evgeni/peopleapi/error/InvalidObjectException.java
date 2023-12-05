package dev.evgeni.peopleapi.error;

import java.util.Map;
import lombok.Getter;

@Getter
public class InvalidObjectException extends PersonApiException {

    private final Map<String, String> constraintViolations;

    public InvalidObjectException(String message, Map<String, String> constraintViolations) {
        super(message);
        this.constraintViolations = constraintViolations;
    }

}
