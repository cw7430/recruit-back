package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;

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
            throw new CustomException(
                    ResponseCode.VALIDATION_ERROR,
                    "gender",
                    "입력 가능값: M, F, 입력된 값: " + value
            );
        }
    }
}