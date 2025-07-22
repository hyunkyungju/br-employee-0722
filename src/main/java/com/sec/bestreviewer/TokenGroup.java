package com.sec.bestreviewer;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TokenGroup {
    private final String type;
    private final List<String> options;
    private final List<String> params;

}
