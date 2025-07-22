package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;
import com.sec.bestreviewer.model.EmployeeField;

public class MonthBirthdayCondition implements Condition {
    private final String value;

    public MonthBirthdayCondition(String value) { this.value = value; }

    @Override
    public boolean matches(Employee employee) {
        return employee.getBirthday().substring(4,6).equals(value);
    }
}