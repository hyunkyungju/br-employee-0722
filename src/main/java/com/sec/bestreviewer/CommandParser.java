package com.sec.bestreviewer;

import com.sec.bestreviewer.condition.ConditionTokens;
import com.sec.bestreviewer.condition.TokenGroup;

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

        // and/or 연산 파싱의 경우, 빈칸도 인덱스에 포함해야 하므로 getValidList 사용 금지!
        ConditionTokens condTokens = null;
        if (!type.equals("ADD") && !type.equals("CNT")) {
            condTokens = parseParams(type, options, params);
        }
        // params는 ADD나 CNT에만 getValidList 적용
        final List<String> validParams = (type.equals("ADD") || type.equals("CNT")) ? getValidList(params) : params;
        return new TokenGroup(type, getValidList(options), validParams, condTokens);
    }

    private List<String> getValidList(final List<String> list) {
        final List<String> validList = new ArrayList<>();
        for (String i : list) {
            if (!EMPTY.equals(i) && !SPACE.equals(i)) {
                validList.add(i);
            }
        }
        return validList;
    }

    private ConditionTokens parseParams(String cmd, List<String> options, List<String> params) {
        // and/or 인덱스는 원본 params에서!
        int idxAnd = params.indexOf("-a");
        int idxOr = params.indexOf("-o");
        boolean isAnd = idxAnd != -1;
        boolean isOr = idxOr != -1;
        int idx = isAnd ? idxAnd : (isOr ? idxOr : -1);

        if (idx != -1) {
            // 조건1 옵션 추출
            List<String> options1 = new ArrayList<>(getValidList(options));
            if (params.get(0).startsWith("-")) options1.add(params.get(0));
            if (params.get(1).startsWith("-")) options1.add(params.get(1));
            String column1 = params.get(0).startsWith("-") ? params.get(2) : params.get(0);
            String value1 = params.get(1).startsWith("-") ? params.get(3) : params.get(1);

            // 조건2 옵션 추출
            int cond2Start = idx + 1;
            List<String> options2 = new ArrayList<>();
            if (params.get(cond2Start).startsWith("-")) options2.add(params.get(cond2Start));
            if (params.get(cond2Start + 1).startsWith("-")) options2.add(params.get(cond2Start + 1));
            String column2 = params.get(cond2Start + 2);
            String value2 = params.get(cond2Start + 3);

            // 변경 컬럼/값 (MOD만)
            String changeColumn = null, changeValue = null;
            if ("MOD".equals(cmd)) {
                changeColumn = params.get(params.size() - 2);
                changeValue = params.get(params.size() - 1);
            }
            return new ConditionTokens(options1, column1, value1, isAnd, isOr, options2, column2, value2, changeColumn, changeValue);
        } else {
            // 단일 조건
            List<String> condOptions = new ArrayList<>(getValidList(options));
            if (params.size() > 0 && params.get(0).startsWith("-")) condOptions.add(params.get(0));
            if (params.size() > 1 && params.get(1).startsWith("-")) condOptions.add(params.get(1));
            String column = params.get(params.size() - 2);
            String value = params.get(params.size() - 1);

            String changeColumn = null, changeValue = null;
            if ("MOD".equals(cmd)) {
                changeColumn = column;
                changeValue = value;
                column = params.get(0);
                value = params.get(1);
            }
            return new ConditionTokens(condOptions, column, value, false, false, null, null, null, changeColumn, changeValue);
        }
    }
}