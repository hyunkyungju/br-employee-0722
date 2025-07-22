package com.sec.bestreviewer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Printer {
    private final String outputFilName;
    private final StringBuilder stdStringBuilder;

    public Printer(final String outputFilename) {
        this(outputFilename, new StringBuilder());
    }

    public Printer(final String outputFilename, final StringBuilder stdStringBuilder) {
        this.outputFilName = outputFilename;
        this.stdStringBuilder = stdStringBuilder;
    }

    public void add(final List<String> lines) {
        for (final String line : lines) {
            stdStringBuilder.append(line + "\n");
        }
    }

    public void printOutputFile() {
        try (OutputStream output = Files.newOutputStream(Paths.get(outputFilName))) {
            output.write(stdStringBuilder.toString().getBytes());
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}
