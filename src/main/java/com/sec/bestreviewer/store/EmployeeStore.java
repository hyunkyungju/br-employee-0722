package com.sec.bestreviewer.store;

import com.sec.bestreviewer.condition.Condition;
import com.sec.bestreviewer.model.Employee;

import java.util.List;

public interface EmployeeStore {
    List<Employee> search(Condition condition);
    List<Employee> modify(Condition condition, String changeColumn, String changeValue);
    List<Employee> delete(Condition condition);
    void add(Employee employee);
    int count();
}
