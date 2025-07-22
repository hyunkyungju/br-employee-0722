package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LastNameConditionTest {

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
    @DisplayName("last name이 존재하는 경우")
    void testMatchesCorrectLastName() {
        String alreadyExistLastName = "KIM";
        Employee employee = Employee.of(employeeNumber, name, careerLevel, phoneNumber, birthday, certi);
        LastNameCondition condition = new LastNameCondition(alreadyExistLastName);

        boolean isMatched = condition.matches(employee);

        assertTrue(isMatched);
    }

    @Test
    @DisplayName("last name이 존재하지 않는 경우")
    void testNoMatchesCorrectLastName() {
        String newLastName = "LEEM";
        Employee employee = Employee.of(employeeNumber, name, careerLevel, phoneNumber, birthday, certi);
        LastNameCondition condition = new LastNameCondition(newLastName);

        boolean isMatched = condition.matches(employee);

        assertFalse(isMatched);
    }

}