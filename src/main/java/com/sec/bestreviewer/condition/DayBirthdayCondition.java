package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public class DayBirthdayCondition implements Condition {
    private final String value;

    public DayBirthdayCondition(String value) { this.value = value; }

    @Override
    public boolean matches(Employee employee) {
        return employee.getBirthday().substring(6,8).equals(value);
    }
}