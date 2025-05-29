package com.example.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NumericValidator implements ConstraintValidator<Numeric, String> {
    @Override
    public boolean isValid(String number, ConstraintValidatorContext context) {
        return number.matches("\\d+");
    }
}
