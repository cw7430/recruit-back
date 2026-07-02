package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;

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
            throw new CustomException(
                    ResponseCode.VALIDATION_ERROR,
                    "submit",
                    "입력 가능값: HIGH_SCHOOL, ASSOCIATE, BACHELOR, MASTER, DOCTORATE, 입력된 값: " + value
            );
        }
    }
}
