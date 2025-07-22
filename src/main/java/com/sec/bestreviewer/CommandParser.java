package com.sec.bestreviewer;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CommandParser {
    private final static int MIN_TOKENS_NUM = 5; // CMD + 3 of options + at least 1 params
    private final static String SPACE = " ";
    private final static String EMPTY = "";

    public TokenGroup parse(final String line) throws IllegalArgumentException {
        final String[] splits = line.split(",", -1);

        if (splits.length < MIN_TOKENS_NUM) {
            throw new IllegalArgumentException("wrong command format");
        }

        final String type = splits[0];
        final List<String> options = Arrays.asList(Arrays.copyOfRange(splits, 1, 4));
        final List<String> params = Arrays.asList(Arrays.copyOfRange(splits, 4, splits.length));

        return new TokenGroup(type, getValidList(options), getValidList(params));
    }

    private List<String> getValidList(final List<String> list) {
        final List<String> validList = new ArrayList<>();

        list.forEach(i -> {
            if (!EMPTY.equals(i) && !SPACE.equals(i)) {
                validList.add(i);
            }
        });

        return validList;
    }
}
