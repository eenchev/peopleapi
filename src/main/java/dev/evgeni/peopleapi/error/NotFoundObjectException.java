package dev.evgeni.peopleapi.error;

import lombok.Getter;

@Getter
public class NotFoundObjectException extends PersonApiException {

    private static final String ERROR_MESSAGE = "Object not found";
    private String entityClass;
    private String entityId;

    public NotFoundObjectException(String entityClass, String entityId) {
        super(ERROR_MESSAGE);
        this.entityClass = entityClass;
        this.entityId = entityId;
    }

}
