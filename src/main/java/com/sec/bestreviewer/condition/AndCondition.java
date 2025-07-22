package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;
import lombok.Getter;

@Getter
public class AndCondition implements Condition {
    private final Condition firstCondition;
    private final Condition secondCondition;

    public AndCondition(Condition firstCondition, Condition secondCondition) {
        this.firstCondition = firstCondition;
        this.secondCondition = secondCondition;
    }

    @Override
    public boolean matches(Employee employee) {
        return firstCondition.matches(employee) && secondCondition.matches(employee);
    }
}