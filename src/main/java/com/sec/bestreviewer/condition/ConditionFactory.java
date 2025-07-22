package com.sec.bestreviewer.condition;

import com.sec.bestreviewer.model.EmployeeField;
import java.util.List;
import java.util.ArrayList;

public class ConditionFactory {
    public static Condition createCondition(List<String> options, List<String> params, String commandType) {
        if (isMultiCondition(params)) {
            return createMultiCondition(options, params, commandType);
        } else {
            return createSingleCondition(options, params);
        }
    }

    private static Condition createSingleCondition(List<String> options, List<String> params) {
        EmployeeField field = EmployeeField.parseField(params.get(0));
        String value = params.get(1);

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

        return new FieldEqualsCondition(field, value);
    }

    private static Condition createMultiCondition(List<String> options, List<String> params, String commandType) {
        int idx = params.contains("-a") ? params.indexOf("-a") : params.indexOf("-o");
        String op = params.get(idx);

        // 조건1 파싱
        List<String> cond1Raw = params.subList(0, idx);
        List<String> cond1Options = new ArrayList<>(options);
        List<String> cond1Params = new ArrayList<>();
        for (String token : cond1Raw) {
            if (token.startsWith("-")) cond1Options.add(token);
            else cond1Params.add(token);
        }
        while (cond1Params.size() > 2) cond1Params.remove(cond1Params.size() - 1);

        // 조건2 파싱, 명령어 타입별 마지막 인덱스 결정
        int cond2EndIdx = params.size();
        if ("MOD".equals(commandType)) {
            cond2EndIdx -= 2;
        }
        List<String> cond2Raw = params.subList(idx + 1, cond2EndIdx);
        List<String> cond2Options = new ArrayList<>();
        List<String> cond2Params = new ArrayList<>();
        for (String token : cond2Raw) {
            if (token.startsWith("-")) cond2Options.add(token);
            else cond2Params.add(token);
        }
        while (cond2Params.size() > 2) cond2Params.remove(cond2Params.size() - 1);

        Condition c1 = createSingleCondition(cond1Options, cond1Params);
        Condition c2 = createSingleCondition(cond2Options, cond2Params);

        return "-a".equals(op) ? new AndCondition(c1, c2) : new OrCondition(c1, c2);
    }

    private static boolean isMultiCondition(List<String> params) {
        return params.contains("-a") || params.contains("-o");
    }
}