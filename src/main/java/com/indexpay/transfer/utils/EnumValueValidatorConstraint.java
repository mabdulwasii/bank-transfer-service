package com.indexpay.transfer.utils;

import com.indexpay.transfer.utils.annotation.ValidEnumValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValueValidatorConstraint implements ConstraintValidator<ValidEnumValue, CharSequence> {
    private List<String> values;

    @Override
    public void initialize(ValidEnumValue validEnumValue) {
        values = Stream.of(validEnumValue.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return values.contains(value.toString());
    }
}