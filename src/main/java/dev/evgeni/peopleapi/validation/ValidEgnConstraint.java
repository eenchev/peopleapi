package dev.evgeni.peopleapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidEgnConstraint implements ConstraintValidator<Egn, String> {

    @Override
    public boolean isValid(String egn, ConstraintValidatorContext context) {
        if (egn != null && egn.length() != 10) {
            return false;
        }
        return true;
    }

}
