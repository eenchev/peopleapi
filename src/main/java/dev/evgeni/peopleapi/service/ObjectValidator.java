package dev.evgeni.peopleapi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Service;
import dev.evgeni.peopleapi.error.InvalidObjectException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Service
public class ObjectValidator {

    private final ValidatorFactory vf = Validation.buildDefaultValidatorFactory();

    private final Validator validation = vf.getValidator();

    private static final String CONSTRAINT_VIOLATION_MESSAGE = "DTO object is invalid";

    public void validate(Object o) {

        Set<ConstraintViolation<Object>> violations = validation.validate(o);

        Map<String, String> constraintViolations = new HashMap<>();
        for (ConstraintViolation<Object> violation : violations) {
            constraintViolations.put(violation.getPropertyPath().toString(),
                    violation.getMessage());
        }

        if (!constraintViolations.isEmpty()) {
            throw new InvalidObjectException(CONSTRAINT_VIOLATION_MESSAGE, constraintViolations);
        }

    }
}
