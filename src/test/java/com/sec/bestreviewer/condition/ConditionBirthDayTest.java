package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.CertiField;
import com.sec.bestreviewer.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConditionBirthDayTest {
    String employeeNumber;
    Employee employee;
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
    void matchesYearTest()  {
        String year = employee.getBirthday().substring(0,4);

        YearBirthdayCondition yearBirthdayCondition = new YearBirthdayCondition(year);
        assertTrue(yearBirthdayCondition.matches(employee));
    }
    @Test
    void matchesYearFailTest()  {
        String invalidYear = "9999";

        YearBirthdayCondition yearBirthdayCondition = new YearBirthdayCondition(invalidYear);
        assertFalse(yearBirthdayCondition.matches(employee));
    }

    @Test
    void matchesEmptyYearTest()  {
        String invalidYear = "";

        YearBirthdayCondition yearBirthdayCondition = new YearBirthdayCondition(invalidYear);
        assertFalse(yearBirthdayCondition.matches(employee));
    }

    @Test
    void matchesMonthTest()  {
        String month = employee.getBirthday().substring(4,6);

        MonthBirthdayCondition monthBirthdayCondition = new MonthBirthdayCondition(month);
        assertTrue(monthBirthdayCondition.matches(employee));
    }

    @Test
    void matchesMonthFailTest()  {
        String invalidMonth = "99";

        YearBirthdayCondition yearBirthdayCondition = new YearBirthdayCondition(invalidMonth);
        assertFalse(yearBirthdayCondition.matches(employee));
    }

    @Test
    void matchesEmptyMonthTest()  {
        String invalidMonth = "";

        YearBirthdayCondition yearBirthdayCondition = new YearBirthdayCondition(invalidMonth);
        assertFalse(yearBirthdayCondition.matches(employee));
    }


    @Test
    void matchesDayTest()  {
        String day = employee.getBirthday().substring(6,8);

        DayBirthdayCondition dayBirthdayCondition = new DayBirthdayCondition(day);
        assertTrue(dayBirthdayCondition.matches(employee));
    }

    @Test
    void matchesDayFailTest()  {
        String invalidDay = "99";

        DayBirthdayCondition dayBirthdayCondition = new DayBirthdayCondition(invalidDay);
        assertFalse(dayBirthdayCondition.matches(employee));
    }

    @Test
    void matchesEmptyDayTest()  {
        String invalidDay = "";

        YearBirthdayCondition yearBirthdayCondition = new YearBirthdayCondition(invalidDay);
        assertFalse(yearBirthdayCondition.matches(employee));
    }
}
