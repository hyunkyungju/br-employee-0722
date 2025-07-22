package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.CertiField;
import com.sec.bestreviewer.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConditionPhoneNumberTest {
    String employeeNumber;
    Employee employee;
    private static final int PHONE_MIDDLE_INDEX = 1;
    private static final int PHONE_LAST_INDEX = 2;
    @BeforeEach
    void setUp() throws IOException {
        employeeNumber = "15087654";
        employee =
                Employee.of(employeeNumber,
                        "SEO KFI",
                        "CL1",
                        "010-1234-5678",
                        "20190101",
                        CertiField.ADV.getFieldName());
    }

    @Test
    void matchesMiddlePhoneNumberTest()  {
        String middleNumber = employee.getPhoneNumber().split("-")[PHONE_MIDDLE_INDEX];

        MiddlePhoneCondition mddlePhoneCondition = new MiddlePhoneCondition(middleNumber);
        assertTrue(mddlePhoneCondition.matches(employee));
    }

    @Test
    void matchesEmptyMiddlePhoneNumberTest()  {
        String invalidMiddleNumber = "";

        MiddlePhoneCondition mddlePhoneCondition = new MiddlePhoneCondition(invalidMiddleNumber);
        assertFalse(mddlePhoneCondition.matches(employee));
    }

    @Test
    void matchesMiddlePhoneNumberFailTest()  {
        String invalidMiddleNumber = "9999";

        MiddlePhoneCondition mddlePhoneCondition = new MiddlePhoneCondition(invalidMiddleNumber);
        assertFalse(mddlePhoneCondition.matches(employee));
    }

    @Test
    void matchesLastPhoneNumberTest()  {
        String lastNumber = employee.getPhoneNumber().split("-")[PHONE_LAST_INDEX];

        LastPhoneCondition lastPhoneCondition = new LastPhoneCondition(lastNumber);
        assertTrue(lastPhoneCondition.matches(employee));
    }

    @Test
    void matchesEmptyLastPhoneNumberTest()  {
        String lastNumber = "";

        LastPhoneCondition lastPhoneCondition = new LastPhoneCondition(lastNumber);
        assertFalse(lastPhoneCondition.matches(employee));
    }

    @Test
    void matchesEmptyLastPhoneNumberFailTest()  {
        String lastNumber = "9999";

        LastPhoneCondition lastPhoneCondition = new LastPhoneCondition(lastNumber);
        assertFalse(lastPhoneCondition.matches(employee));
    }
}
