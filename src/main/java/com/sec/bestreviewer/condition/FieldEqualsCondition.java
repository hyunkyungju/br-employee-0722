package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.EmployeeField;
import com.sec.bestreviewer.model.Employee;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FieldEqualsCondition implements Condition {

    private final EmployeeField field;
    private final String value;

    @Override
    public boolean matches(Employee employee) {
        final String employeeFieldValue = employee.getFieldValue(field);
        return employeeFieldValue != null && employeeFieldValue.equals(value);
    }
}