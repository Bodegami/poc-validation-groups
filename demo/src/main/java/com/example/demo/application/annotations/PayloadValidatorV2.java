package com.example.demo.application.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PayloadValidatorV2Impl.class)
public @interface PayloadValidatorV2 {

    String message() default "Payload inválido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
