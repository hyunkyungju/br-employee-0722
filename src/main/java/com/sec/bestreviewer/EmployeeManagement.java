package com.sec.bestreviewer;

import com.sec.bestreviewer.command.Command;
import com.sec.bestreviewer.condition.TokenGroup;

import java.util.Collections;
import java.util.List;

public class EmployeeManagement  {
    public void run(final String[] args) {
        if (args.length < 2) {
            printUsage();
            throw new IllegalArgumentException("wrong arguments count");
        }

        final String inputFileName = args[0];
        final String outputFilename = args[1];

        final CommandReader commandReader = new CommandReader(inputFileName);
        final CommandParser commandParser = new CommandParser();
        final CommandFactory commandFactory = new CommandFactory();
        final CommandExecutor commandExecutor = new CommandExecutor();
        final Printer printer = new Printer(outputFilename);

        final List<String> input = commandReader.readFile();

        for (final String line : input) {
            try {
                final TokenGroup tokens = commandParser.parse(line);
                final Command command = commandFactory.buildCommand(tokens.getType(), tokens.getOptions(), tokens.getParams(), tokens.getCondTokens());
                final List<String> result = commandExecutor.execute(command);
                printer.add(result);
            } catch (IllegalArgumentException exception) {
                printer.add(Collections.singletonList("wrong command : " + line));
            }
        }

        printer.printOutputFile();
    }

    private void printUsage() {
        System.out.println("input / output 형태는 txt 파일이며, 아래 command 로 input file 을 read 하여, output file 을 생성한다.");
        System.out.println("Usage : EmployeeManagement input.txt output.txt");
    }

    public static void main(final String[] args) {
        final EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.run(args);
    }
}