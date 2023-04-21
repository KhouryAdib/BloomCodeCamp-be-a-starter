package com.hcc.enums;

public enum AuthorityEnum {
    LEARNER("LEARNER"),
    REVIEWER("REVIEWER"),
    ADMIN("ADMIN");


    private final String value;

    AuthorityEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
