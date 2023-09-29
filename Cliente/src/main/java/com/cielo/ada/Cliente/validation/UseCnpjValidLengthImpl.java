package com.cielo.ada.Cliente.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UseCnpjValidLengthImpl implements ConstraintValidator<UseCnpjValidLength, String> {
    @Override
    public void initialize(UseCnpjValidLength constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String cnpj, ConstraintValidatorContext context) {
        if (cnpj.length() == 14) {
            return true;
        }
        return false;
    }
}
