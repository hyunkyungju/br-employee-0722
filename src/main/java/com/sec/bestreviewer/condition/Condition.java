package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;

public interface Condition {
    boolean matches(Employee employee);
}