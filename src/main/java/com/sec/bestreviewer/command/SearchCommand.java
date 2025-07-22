package com.sec.bestreviewer.command;

import com.sec.bestreviewer.CommandFactory;
import com.sec.bestreviewer.condition.Condition;
import com.sec.bestreviewer.model.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import com.sec.bestreviewer.util.ResultStringFormatter;
import lombok.AllArgsConstructor;

import java.util.List;

import static com.sec.bestreviewer.CommandExecutor.MAX_RESULT_NUMBER;

@AllArgsConstructor
public class SearchCommand implements Command {
    private final OptionParser optionParser;
    private final Condition condition;

    @Override
    public List<String> execute(final EmployeeStore employeeStore) {
        final List<Employee> employeeList = employeeStore.search(condition);
        if (optionParser.isPrintOn()) {
            return ResultStringFormatter.getEmployeeListToFormattedString(
                    employeeList, CommandFactory.CMD_SCH, MAX_RESULT_NUMBER);
        }
        return ResultStringFormatter.getEmployeeListToFormattedString(employeeList, CommandFactory.CMD_SCH);
    }
}
