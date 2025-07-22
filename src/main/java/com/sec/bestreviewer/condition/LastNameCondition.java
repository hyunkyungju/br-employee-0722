package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public class LastNameCondition implements Condition {

    private final String lastName;

    public LastNameCondition(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean matches(Employee employee) {
        return lastName.equals(employee.getLastName());
    }
}
