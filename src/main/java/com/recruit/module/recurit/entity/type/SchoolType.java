package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SchoolType {
    HIGH_SCHOOL, ASSOCIATE, BACHELOR, MASTER, DOCTORATE;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static SchoolType from(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return SchoolType.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
