package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;

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
            throw new CustomException(
                    ResponseCode.VALIDATION_ERROR,
                    "division",
                    "입력 가능값: GRADUATED, ENROLLED, DROPPED_OUT, 입력된 값: " + value
            );
        }
    }
}
