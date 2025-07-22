package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.CertiField;
import com.sec.bestreviewer.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
/*
public class ConditionMultiOptionTest {
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
    @DisplayName("실제 데이터 테스트 And")
    void matchesAndTest()  {
        final List<String> options_condition1 = Collections.singletonList("-p");
        final List<String> params_condition1 = Arrays.asList("name", "SEO KFI");

        Condition condition = ConditionFactory.createCondition(options_condition1, params_condition1);

        AndCondition andCondition = new AndCondition(condition, condition);
        assertTrue(andCondition.matches(employee));
    }

    @Test
    @DisplayName("실제 데이터 테스트 or")
    void matchesOrTest()  {
        final List<String> options_condition1 = Collections.singletonList("-p");
        final List<String> params_condition1 = Arrays.asList("name", "SEO KFI");

        final List<String> options_condition2 = Collections.singletonList("-p");
        final List<String> params_condition2 = Arrays.asList("name", "Andi KIM");

        Condition firstCondition = ConditionFactory.createCondition(options_condition1, params_condition1, "SCH");
        Condition secondCondition = ConditionFactory.createCondition(options_condition2, params_condition2, "SCH");

        OrCondition orCondition = new OrCondition(firstCondition, secondCondition);
        assertTrue(orCondition.matches(employee));
    }

    @Test
    @DisplayName("mock 객체 사용 And")
    void andConditionTrueFalse() {
        Condition trueCond = mock(Condition.class);
        Condition falseCond = mock(Condition.class);
        when(trueCond.matches(any())).thenReturn(true);
        when(falseCond.matches(any())).thenReturn(false);

        AndCondition andCondition = new AndCondition(trueCond, falseCond);
        assertFalse(andCondition.matches(employee)); // true && false == false
    }

    @Test
    @DisplayName("mock 객체 사용 Or")
    void orConditionTrueFalse() {
        Condition trueCond = mock(Condition.class);
        Condition falseCond = mock(Condition.class);
        when(trueCond.matches(any())).thenReturn(true);
        when(falseCond.matches(any())).thenReturn(false);

        OrCondition orCondition = new OrCondition(trueCond, falseCond);
        assertTrue(orCondition.matches(employee)); // true || false == true
    }
}

 */