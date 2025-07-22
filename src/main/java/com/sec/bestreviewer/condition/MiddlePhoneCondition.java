package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public class MiddlePhoneCondition implements Condition {
    private final String value;

    public MiddlePhoneCondition(String value) {
        this.value = value;
    }

    @Override
    public boolean matches(Employee employee) {
        String[] nums = employee.getPhoneNumber().split("-");
        return nums.length > 1 && nums[1].equals(value);
    }

}