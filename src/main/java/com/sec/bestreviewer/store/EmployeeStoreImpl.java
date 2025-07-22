package com.sec.bestreviewer.store;

import com.sec.bestreviewer.condition.Condition;
import com.sec.bestreviewer.model.Employee;
import com.sec.bestreviewer.model.EmployeeField;

import java.util.*;
import java.util.stream.Collectors;

import static com.sec.bestreviewer.model.EmployeeField.*;

public class EmployeeStoreImpl implements EmployeeStore {
    private final List<Employee> employees = new ArrayList<>();

    // 인덱스를 Set으로 변경
    private final Map<String, Employee> byEmployeeNum = new HashMap<>();
    private final Map<String, Set<Employee>> byName = new HashMap<>();
    private final Map<String, Set<Employee>> byCareerLevel = new HashMap<>();
    private final Map<String, Set<Employee>> byPhoneNum = new HashMap<>();
    private final Map<String, Set<Employee>> byBirthday = new HashMap<>();
    private final Map<String, Set<Employee>> byCerti = new HashMap<>();
    // 사원번호 정렬용 TreeMap (정렬 옵션 -p 출력 빠름)
    private final TreeMap<String, Employee> byEmployeeNumSorted = new TreeMap<>();

    @Override
    public List<Employee> search(Condition condition) {
        // FieldEqualsCondition은 인덱스 사용
        if (condition instanceof com.sec.bestreviewer.condition.FieldEqualsCondition) {
            com.sec.bestreviewer.condition.FieldEqualsCondition fc = (com.sec.bestreviewer.condition.FieldEqualsCondition) condition;
            EmployeeField field = fc.getField();
            String value = fc.getValue();
            switch (field) {
                case EMPLOYEE_NUMBER:
                    Employee emp = byEmployeeNum.get(value);
                    return emp == null ? Collections.emptyList() : Collections.singletonList(emp);
                case NAME:
                    return new ArrayList<>(byName.getOrDefault(value, Collections.emptySet()));
                case CAREER_LEVEL:
                    return new ArrayList<>(byCareerLevel.getOrDefault(value, Collections.emptySet()));
                case PHONE_NUMBER:
                    return new ArrayList<>(byPhoneNum.getOrDefault(value, Collections.emptySet()));
                case BIRTHDAY:
                    return new ArrayList<>(byBirthday.getOrDefault(value, Collections.emptySet()));
                case CERTI:
                    return new ArrayList<>(byCerti.getOrDefault(value, Collections.emptySet()));
            }
        }
        // AND/OR 조건: 교집합/합집합 처리
        if (condition instanceof com.sec.bestreviewer.condition.AndCondition) {
            com.sec.bestreviewer.condition.AndCondition ac = (com.sec.bestreviewer.condition.AndCondition) condition;
            List<Employee> first = search(ac.getFirstCondition());
            List<Employee> second = search(ac.getSecondCondition());
            // Set retainAll (교집합)
            Set<Employee> set = new HashSet<>(first);
            set.retainAll(second);
            return new ArrayList<>(set);
        }
        if (condition instanceof com.sec.bestreviewer.condition.OrCondition) {
            com.sec.bestreviewer.condition.OrCondition oc = (com.sec.bestreviewer.condition.OrCondition) condition;
            List<Employee> first = search(oc.getFirstCondition());
            List<Employee> second = search(oc.getSecondCondition());
            // Set addAll (합집합)
            Set<Employee> set = new HashSet<>(first);
            set.addAll(second);
            return new ArrayList<>(set);
        }
        // 옵션/부등호 등 기타 조건은 전체 employees 순회
        return employees.stream().filter(condition::matches).collect(Collectors.toList());
    }

    @Override
    public List<Employee> delete(Condition condition) {
        final List<Employee> searchedEmployees = search(condition);
        for (Employee emp : searchedEmployees) {
            employees.remove(emp);
            byEmployeeNum.remove(emp.getEmployeeNumber());
            byEmployeeNumSorted.remove(emp.getEmployeeNumber());
            removeFromIndex(byName, emp.getName(), emp);
            removeFromIndex(byCareerLevel, emp.getCareerLevel(), emp);
            removeFromIndex(byPhoneNum, emp.getPhoneNumber(), emp);
            removeFromIndex(byBirthday, emp.getBirthday(), emp);
            removeFromIndex(byCerti, emp.getCerti(), emp);
        }
        return searchedEmployees;
    }

    @Override
    public List<Employee> modify(Condition condition, String changeColumn, String changeValue) {
        final List<Employee> modifyEmployees = search(condition);
        final EmployeeField changeField = EmployeeField.parseField(changeColumn);
        if (changeField == null || changeField.equals(EMPLOYEE_NUMBER)) {
            return new ArrayList<>();
        }
        final List<Employee> originalEmployees = modifyEmployees.stream()
                .map(Employee::new)
                .collect(Collectors.toList());
        for (final Employee employee : modifyEmployees) {
            // 인덱스에서 기존 값 제거
            removeFromIndex(getIndexMap(changeField), employee.getFieldValue(changeField), employee);
            // 실제 값 변경
            employee.setFieldValue(changeField, changeValue);
            // 인덱스에 새 값 추가
            addToIndex(getIndexMap(changeField), changeValue, employee);
        }
        return originalEmployees;
    }

    @Override
    public void add(Employee employee) {
        employees.add(employee);
        byEmployeeNum.put(employee.getEmployeeNumber(), employee);
        byEmployeeNumSorted.put(employee.getEmployeeNumber(), employee);
        addToIndex(byName, employee.getName(), employee);
        addToIndex(byCareerLevel, employee.getCareerLevel(), employee);
        addToIndex(byPhoneNum, employee.getPhoneNumber(), employee);
        addToIndex(byBirthday, employee.getBirthday(), employee);
        addToIndex(byCerti, employee.getCerti(), employee);
    }

    @Override
    public int count() {
        return employees.size();
    }

    // 인덱스 관리
    private void addToIndex(Map<String, Set<Employee>> index, String key, Employee emp) {
        index.computeIfAbsent(key, k -> new HashSet<>()).add(emp);
    }
    private void removeFromIndex(Map<String, Set<Employee>> index, String key, Employee emp) {
        Set<Employee> set = index.get(key);
        if (set != null) {
            set.remove(emp);
            if (set.isEmpty()) index.remove(key);
        }
    }
    private Map<String, Set<Employee>> getIndexMap(EmployeeField field) {
        switch (field) {
            case NAME: return byName;
            case CAREER_LEVEL: return byCareerLevel;
            case PHONE_NUMBER: return byPhoneNum;
            case BIRTHDAY: return byBirthday;
            case CERTI: return byCerti;
            default: return null;
        }
    }
}