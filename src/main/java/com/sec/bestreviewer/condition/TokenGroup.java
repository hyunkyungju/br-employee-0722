package com.sec.bestreviewer.condition;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TokenGroup {
    private final String type;
    private final List<String> options;
    private final List<String> params; // ADD 등에서 사용
    private final ConditionTokens condTokens; // MOD/DEL/SCH 등에서 사용
}