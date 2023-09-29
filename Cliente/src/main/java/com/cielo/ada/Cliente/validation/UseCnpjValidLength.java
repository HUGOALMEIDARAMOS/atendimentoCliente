package com.cielo.ada.Cliente.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UseCnpjValidLengthImpl.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCnpjValidLength {
    String message() default "Cnpj Inválido, necessário possuir 14 digitos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
