package com.example.demo.application.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.ReportAsSingleViolation;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PayloadValidatorImpl.class)
public @interface PayloadValidator {

    String message() default "Payload inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
