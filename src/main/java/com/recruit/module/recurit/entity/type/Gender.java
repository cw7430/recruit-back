package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Gender {
    M, F;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static Gender from(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Gender.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}