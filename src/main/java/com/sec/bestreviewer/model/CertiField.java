package com.sec.bestreviewer.model;

import lombok.Getter;

@Getter
public enum CertiField {
    ADV("adv"),
    PRO("pro"),
    EX("ex");

    private final String fieldName;

    CertiField(String fieldName) {
        this.fieldName = fieldName;
    }

    public int getIndex() {
        return ordinal();
    }
}
