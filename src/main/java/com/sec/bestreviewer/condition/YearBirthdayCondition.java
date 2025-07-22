package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public class YearBirthdayCondition implements  Condition{
    private final String value;

    public YearBirthdayCondition(String value) { this.value = value; }

    @Override
    public boolean matches(Employee employee) {
        return employee.getBirthday().substring(0,4).equals(value);
    }
}
