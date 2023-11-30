package dev.evgeni.peopleapi.error;

import lombok.Getter;

@Getter
public class MissingFileUploadException extends PersonApiException {

    private static final String ERROR_MESSAGE = "Missing required file upload";
    private String entityClass;
    private String fileUploadKey;

    public MissingFileUploadException(String fileUploadKey, String entityClass) {
        super(ERROR_MESSAGE);
        this.fileUploadKey = fileUploadKey;
        this.entityClass = entityClass;
    }

}
