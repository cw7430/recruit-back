package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Division {
    GRADUATED, ENROLLED, DROPPED_OUT;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static Division from(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Division.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
