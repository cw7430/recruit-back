package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum WorkType {
    FULL_TIME, NON_REGULAR;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static WorkType from(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return WorkType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
