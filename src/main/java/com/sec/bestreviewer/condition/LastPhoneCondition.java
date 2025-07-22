package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public class LastPhoneCondition implements Condition {
    private final String value;

    public LastPhoneCondition(String value) {
        this.value = value;
    }

    @Override
    public boolean matches(Employee employee) {
        String[] nums = employee.getPhoneNumber().split("-");
        return nums.length > 2 && nums[2].equals(value);
    }

}