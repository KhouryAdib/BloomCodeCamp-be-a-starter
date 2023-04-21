package com.hcc.enums;

public enum AssignmentEnum {
    CLAIMED("Claimed"),
    UNCLAIMED("Unclaimed");


    private final String value;

    private AssignmentEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
