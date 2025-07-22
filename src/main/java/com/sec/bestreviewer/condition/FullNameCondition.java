package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public class FullNameCondition implements Condition {
    private final String fullName;

    public FullNameCondition(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public boolean matches(Employee employee) {
        return employee.getName().equals(fullName);
    }
}
