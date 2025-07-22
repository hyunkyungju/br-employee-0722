package com.sec.bestreviewer.condition;

import lombok.Data;

import java.util.List;

@Data
public class ConditionTokens {
    public final List<String> options1;
    public final String column1;
    public final String value1;
    public final List<String> options2;
    public final String column2;
    public final String value2;
    public final boolean isAnd;
    public final boolean isOr;
    public final String changeColumn;
    public final String changeValue;

    // 단일 조건용 생성자
    public ConditionTokens(List<String> options1, String column1, String value1) {
        this.options1 = options1;
        this.column1 = column1;
        this.value1 = value1;
        this.options2 = null;
        this.column2 = null;
        this.value2 = null;
        this.isAnd = false;
        this.isOr = false;
        this.changeColumn = null;
        this.changeValue = null;
    }

    // AND/OR 조건용 생성자 (MOD 명령어의 변경 컬럼/값도 포함)
    public ConditionTokens(List<String> options1, String column1, String value1, boolean isAnd, boolean isOr,
                           List<String> options2, String column2, String value2,
                           String changeColumn, String changeValue) {
        this.options1 = options1;
        this.column1 = column1;
        this.value1 = value1;
        this.isAnd = isAnd;
        this.isOr = isOr;
        this.options2 = options2;
        this.column2 = column2;
        this.value2 = value2;
        this.changeColumn = changeColumn;
        this.changeValue = changeValue;
    }

    public boolean isMulti() { return isAnd || isOr; }
}