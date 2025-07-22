package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public class FirstNameCondition implements Condition {
    private final String firstName;

    public FirstNameCondition(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean matches(Employee employee) {
        return firstName.equals(employee.getFirstName());
    }
}
