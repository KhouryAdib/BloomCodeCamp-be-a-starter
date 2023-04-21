package com.hcc.enums;

public enum AssignmentStatusEnum {
    WORKINGON("WorkingOn"),
    SUBMITTED("Submitted"),
    COMPLETED("Completed"),
    REJECTED("Rejected");


    private final String value;

    private AssignmentStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
