package dev.evgeni.peopleapi.web;

import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import dev.evgeni.peopleapi.error.AuthenticationFailedException;
import dev.evgeni.peopleapi.error.InvalidObjectException;
import dev.evgeni.peopleapi.error.MissingFileUploadException;
import dev.evgeni.peopleapi.error.NotFoundObjectException;
import lombok.Builder;
import lombok.Data;

@RestControllerAdvice
public class ExceptionHandlerAdvice {


    // handle entity not found
    @ExceptionHandler(NotFoundObjectException.class)
    public ResponseEntity<PeopleApiHttpException> entityNotFound(NotFoundObjectException ex) {
        PeopleApiHttpException httpEx = PeopleApiHttpException.builder().errorId(ex.getId())
                .message(ex.getMessage() + ". Not found ID: " + ex.getEntityId())
                .clazz(ex.getEntityClass()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(httpEx);
    }

    @ExceptionHandler(MissingFileUploadException.class)
    public ResponseEntity<PeopleApiHttpException> missingUpload(MissingFileUploadException ex) {
        PeopleApiHttpException httpEx = PeopleApiHttpException.builder().errorId(ex.getId())
                .message(ex.getMessage() + ". File should be at key: " + ex.getFileUploadKey())
                .clazz(ex.getEntityClass()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpEx);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<PeopleApiHttpException> methodNotAllowed(
            HttpRequestMethodNotSupportedException ex) {
        PeopleApiHttpException httpEx = PeopleApiHttpException.builder().errorId(UUID.randomUUID())
                .message("Method not allowed: " + ex.getMethod()).build();
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(httpEx);
    }

    @ExceptionHandler(InvalidObjectException.class)
    public ResponseEntity<PeopleApiHttpException> invalidObject(InvalidObjectException e) {
        PeopleApiHttpException httpEx = PeopleApiHttpException.builder().errorId(e.getId())
                .message(e.getMessage()).errors(e.getConstraintViolations()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpEx);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<PeopleApiHttpException> authenticationFailed(
            AuthenticationFailedException e) {
        PeopleApiHttpException httpEx = PeopleApiHttpException.builder().errorId(e.getId())
                .message(e.getMessage() + ". Cause: " + e.getConcreteError()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpEx);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<PeopleApiHttpException> illegalArgument(IllegalArgumentException e) {
        PeopleApiHttpException httpEx = PeopleApiHttpException.builder().errorId(UUID.randomUUID())
                .message(e.getMessage()).build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(httpEx);
    }

    @Data
    @Builder
    private static class PeopleApiHttpException {
        private UUID errorId;
        private String message;
        private String clazz;
        private Map<String, String> errors;
    }

}
