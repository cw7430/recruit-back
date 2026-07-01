package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Submit {
    N, Y;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static Submit from(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Submit.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
