package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullNameConditionTest {

    String employeeNumber;
    String name;
    String careerLevel;
    String phoneNumber;
    String birthday;
    String certi; // TODO: CertiField로 타입 변경 예정

    @BeforeEach
    void setUp() {
        employeeNumber = "90001446";
        name = "YUJIN KIM";
        careerLevel = "CL2";
        phoneNumber = "010-1234-1234";
        birthday = "19990909";
        certi = "EX";
    }

    @Test
    @DisplayName("풀네임이 일치하는지 확인")
    void testCheckingFullNameIsMatched() {
        String alreadyExistFullName = "YUJIN KIM";
        Employee employee = Employee.of(employeeNumber, name, careerLevel, phoneNumber, birthday, certi);
        FullNameCondition condition = new FullNameCondition(alreadyExistFullName);

        boolean isMatched = condition.matches(employee);

        assertTrue(isMatched);
    }

    @Test
    @DisplayName("풀네임이 불일치하는지 확인")
    void testCheckingFullNameIsMisMatched() {
        String alreadyExistFullName = "YUJIN LEEM";
        Employee employee = Employee.of(employeeNumber, name, careerLevel, phoneNumber, birthday, certi);
        FullNameCondition condition = new FullNameCondition(alreadyExistFullName);

        boolean isMatched = condition.matches(employee);

        assertFalse(isMatched);
    }
}
