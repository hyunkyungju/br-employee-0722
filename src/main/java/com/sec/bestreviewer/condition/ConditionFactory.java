package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.EmployeeField;
import java.util.List;

public class ConditionFactory {
    public static Condition createCondition(ConditionTokens tokens) {
        if (tokens.isMulti()) {
            Condition c1 = createSingleCondition(tokens.options1, tokens.column1, tokens.value1);
            Condition c2 = createSingleCondition(tokens.options2, tokens.column2, tokens.value2);
            return tokens.isAnd ? new AndCondition(c1, c2) : new OrCondition(c1, c2);
        } else {
            return createSingleCondition(tokens.options1, tokens.column1, tokens.value1);
        }
    }

    private static Condition createSingleCondition(List<String> options, String column, String value) {
        EmployeeField field = EmployeeField.parseField(column);

        if (options.contains("-y") && field == EmployeeField.BIRTHDAY)
            return new YearBirthdayCondition(value);
        if (options.contains("-m") && field == EmployeeField.BIRTHDAY)
            return new MonthBirthdayCondition(value);
        if (options.contains("-d") && field == EmployeeField.BIRTHDAY)
            return new DayBirthdayCondition(value);
        if (options.contains("-m") && field == EmployeeField.PHONE_NUMBER)
            return new MiddlePhoneCondition(value);
        if (options.contains("-l") && field == EmployeeField.PHONE_NUMBER)
            return new LastPhoneCondition(value);
        if (options.contains("-f") && field == EmployeeField.NAME)
            return new FirstNameCondition(value);
        if (options.contains("-l") && field == EmployeeField.NAME)
            return new LastNameCondition(value);

        // 부등호 등 기타 옵션 추가 필요 시 여기에!
        return new FieldEqualsCondition(field, value);
    }
}