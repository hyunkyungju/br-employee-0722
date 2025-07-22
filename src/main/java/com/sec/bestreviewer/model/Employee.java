package com.sec.bestreviewer.model;


import com.sec.bestreviewer.util.FullNameValidator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Employee {
    @NonNull private final String employeeNumber;
    @NonNull private String name;
    @NonNull private String careerLevel;
    @NonNull private String phoneNumber;
    @NonNull private String birthday;
    @NonNull private String certi;

    // 복사 생성자 추가
    public Employee(Employee other) {
        this.employeeNumber = other.employeeNumber;
        this.name = other.name;
        this.careerLevel = other.careerLevel;
        this.phoneNumber = other.phoneNumber;
        this.birthday = other.birthday;
        this.certi = other.certi;
    }

    public static Employee of(String employeeNumber, String fullName, String careerLevel, String phoneNumber, String birthday, String certi) {
        if (!FullNameValidator.isValidFullName(fullName)) {
            throw new IllegalArgumentException("fullName 형식이 올바르지 않습니다.");
        }
        return new Employee(employeeNumber, fullName, careerLevel, phoneNumber, birthday, certi);
    }

    public String getFirstName() {
        return name.split(" ")[0];
    }

    public String getLastName() {
        return name.split(" ")[1];
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) return false;
        final Employee employee = (Employee) object;
        return Objects.equals(employeeNumber, employee.employeeNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeNumber);
    }

    public String getFieldValue(EmployeeField field) {
        switch (field) {
            case EMPLOYEE_NUMBER:
                return employeeNumber;
            case NAME:
                return name;
            case CAREER_LEVEL:
                return careerLevel;
            case PHONE_NUMBER:
                return phoneNumber;
            case BIRTHDAY:
                return birthday;
            case CERTI:
                return certi;
            default:
                return null;
        }
    }

    public void setFieldValue(EmployeeField employeeField, String value) {
        switch (employeeField) {
            case EMPLOYEE_NUMBER:
                // employeeNumber는 final이므로 set 불가
                break;
            case NAME:
                this.name = value;
                break;
            case CAREER_LEVEL:
                this.careerLevel = value;
                break;
            case PHONE_NUMBER:
                this.phoneNumber = value;
                break;
            case BIRTHDAY:
                this.birthday = value;
                break;
            case CERTI:
                this.certi = value;
                break;
            default:
                break;
        }
    }
}
