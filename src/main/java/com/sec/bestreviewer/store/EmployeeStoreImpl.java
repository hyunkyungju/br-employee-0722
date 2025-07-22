package com.sec.bestreviewer.store;

import com.sec.bestreviewer.condition.Condition;
import com.sec.bestreviewer.model.Employee;
import com.sec.bestreviewer.model.EmployeeField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.model.EmployeeField.EMPLOYEE_NUMBER;


class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public List<Employee> search(Condition condition) {
        return employees.stream().filter(condition::matches).collect(Collectors.toList());
    }

    @Override
    public List<Employee> delete(Condition condition) {
        final List<Employee> searchedEmployees = search(condition);
        employees.removeAll(searchedEmployees);
        return searchedEmployees;
    }

    @Override
    public List<Employee> modify(Condition condition, String changeColumn, String changeValue) {
        final List<Employee> modifyEmployees = search(condition);
        final EmployeeField changeField = EmployeeField.parseField(changeColumn);
        if (changeField == null) {
            throw new IllegalArgumentException("Invalid field name: " + changeColumn);
        }

        if (changeField.equals(EMPLOYEE_NUMBER)) {
            return new ArrayList<>();
        }

        final List<Employee> originalEmployees = modifyEmployees.stream()
                .map(Employee::new)  // 복사 생성자 사용
                .collect(Collectors.toList());
        for (final Employee employee : modifyEmployees) {
            employee.setFieldValue(changeField, changeValue);
        }
        return originalEmployees;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
    }

    @Override
    public int count() {
        return employees.size();
    }
}
