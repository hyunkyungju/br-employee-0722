package com.sec.bestreviewer;

import com.sec.bestreviewer.command.*;
import com.sec.bestreviewer.condition.Condition;
import com.sec.bestreviewer.condition.ConditionFactory;
import com.sec.bestreviewer.model.EmployeeField;
import com.sec.bestreviewer.model.Employee;
import com.sec.bestreviewer.util.OptionParser;

import java.util.List;

public class CommandFactory {
    public static final String CMD_ADD = "ADD";
    public static final String CMD_DEL = "DEL";
    public static final String CMD_SCH = "SCH";
    public static final String CMD_CNT = "CNT";
    public static final String CMD_MOD = "MOD";

    static Command buildCommand(final String cmd, final List<String> options, final List<String> params) throws IllegalArgumentException {
        final OptionParser optionParser = new OptionParser(options);

        switch (cmd) {
            case CMD_ADD:
                final Employee employee =
                        Employee.of(params.get(EmployeeField.EMPLOYEE_NUMBER.ordinal()), params.get(EmployeeField.NAME.ordinal()),
                                params.get(EmployeeField.CAREER_LEVEL.ordinal()), params.get(EmployeeField.PHONE_NUMBER.ordinal()),
                                params.get(EmployeeField.BIRTHDAY.ordinal()), params.get(EmployeeField.CERTI.ordinal()));
                return new AddCommand(optionParser, employee);
            case CMD_DEL: {
                final Condition condition = ConditionFactory.createCondition(options, params, CMD_DEL);
                return new DeleteCommand(optionParser, condition);
            }
            case CMD_SCH: {
                final Condition condition = ConditionFactory.createCondition(options, params, CMD_SCH);
                return new SearchCommand(optionParser, condition);
            }
            case CMD_MOD: {
                final Condition condition = ConditionFactory.createCondition(options, params, CMD_MOD);
                final String changeColumn = params.get(params.size() - 2);
                final String changeValue = params.get(params.size() - 1);
                return new ModifyCommand(optionParser, condition, changeColumn, changeValue);
            }
            case CMD_CNT:
                return new CountCommand();
        }
        throw new IllegalArgumentException("Wrong command");
    }
}