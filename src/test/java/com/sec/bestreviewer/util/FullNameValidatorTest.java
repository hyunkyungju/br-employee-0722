package com.sec.bestreviewer.util;

import com.sec.bestreviewer.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FullNameValidatorTest {
    String employeeNumber;
    String careerLevel;
    String phoneNumber;
    String birthday;
    String certi; // TODO: CertiField로 타입 변경 예정

    @BeforeEach
    void setUp() {
        employeeNumber = "90001446";
        careerLevel = "CL2";
        phoneNumber = "010-1234-1234";
        birthday = "19990909";
        certi = "EX";
    }

    @Test
    @DisplayName("유효하지 않은 name이 들어간 경우 에러 발생")
    void testInvalidName() {
        String invalidFullName = "adsfdfFDFDFDfjkasdfASD";
        assertThrows(IllegalArgumentException.class, () -> Employee.of(employeeNumber, invalidFullName, careerLevel, phoneNumber, birthday, certi));
    }

    @Test
    @DisplayName("이름에 소문자가 포함된 경우 실패")
    void testContainsLowerCase() {
        assertFalse(FullNameValidator.isValidFullName("JISOO Kim"));
        assertFalse(FullNameValidator.isValidFullName("JiSoo KIM"));
        assertFalse(FullNameValidator.isValidFullName("jisoo kim"));
        assertTrue(FullNameValidator.isValidFullName("JISOO KIM"));
    }

    @Test
    @DisplayName("이름이 15자리가 넘는 경우 실패")
    void testOver15Character() {
        String sixteenCharacters1 = "KIMABCDEFGHIJKLM";
        String sixteenCharactersWithSpace = "KIM JISOOABCDQWE";
        assertFalse(FullNameValidator.isValidFullName(sixteenCharacters1));
        assertFalse(FullNameValidator.isValidFullName(sixteenCharactersWithSpace));
    }
}
