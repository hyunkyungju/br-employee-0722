package com.sec.bestreviewer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CommandReader {
    String inputFileName;

    CommandReader(final String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public List<String> readFile() {
        final List<String> list = new ArrayList<>();
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFileName))) {
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                list.add(line);
            }
        } catch (FileNotFoundException exception) {
            throw new IllegalArgumentException("Input file " + inputFileName + " is NOT exist");
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return list;
    }
}
