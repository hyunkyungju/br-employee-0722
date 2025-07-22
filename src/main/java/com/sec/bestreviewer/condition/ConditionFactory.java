package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.EmployeeField;

import java.util.List;

public class ConditionFactory {
    private static final int COMMAND_FIELD_KEY = 0;
    private static final int COMMAND_FIELD_VALUE = 1;
    public static Condition createCondition(List<String> options, List<String> params) {
        if (isMultiCondition(params)) {
            return createMultiCondition(options, params);
        } else {
            return createSingleCondition(options, params);
        }
    }

    private static Condition createSingleCondition(List<String> options, List<String> params) {
        EmployeeField field = EmployeeField.parseField(params.get(COMMAND_FIELD_KEY));
        String value = params.get(COMMAND_FIELD_VALUE);

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

        return new FieldEqualsCondition(field, value);
    }

    private static Condition createMultiCondition(List<String> options, List<String> params) {
        int idx = params.contains("-a") ? params.indexOf("-a") : params.indexOf("-o");
        String op = params.get(idx);
        List<String> cond1 = params.subList(0, idx); // [col, val]
        List<String> cond2 = params.subList(idx+1, idx+3); // [col, val]
        Condition c1 = createSingleCondition(options, cond1);
        Condition c2 = createSingleCondition(options, cond2);
        return "-a".equals(op) ? new AndCondition(c1, c2) : new OrCondition(c1, c2);
    }

    private static boolean isMultiCondition(List<String> params) {
        return params.contains("-a") || params.contains("-o");
    }
}