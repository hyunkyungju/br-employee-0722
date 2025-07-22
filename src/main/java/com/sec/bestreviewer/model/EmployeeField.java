package com.sec.bestreviewer.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EmployeeField {
    EMPLOYEE_NUMBER("employeeNum"),
    NAME("name"),
    CAREER_LEVEL("cl"),
    PHONE_NUMBER("phoneNum"),
    BIRTHDAY("birthday"),
    CERTI("certi");

    private final String fieldName;

    EmployeeField(String fieldName) {
        this.fieldName = fieldName;
    }

    public static EmployeeField parseField(String input) {
        return Arrays.stream(EmployeeField.values())
                .filter(field -> field.getFieldName().equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown field: " + input));
    }
}
