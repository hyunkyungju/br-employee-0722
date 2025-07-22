package com.sec.bestreviewer.command;


import com.sec.bestreviewer.model.Employee;
import com.sec.bestreviewer.store.EmployeeStore;
import com.sec.bestreviewer.util.OptionParser;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class AddCommand implements Command {
    private final OptionParser optionParser;
    private final Employee employee;

    @Override
    public List<String> execute(final EmployeeStore employeeStore) {
        employeeStore.add(employee);
        return Collections.emptyList();
    }
}