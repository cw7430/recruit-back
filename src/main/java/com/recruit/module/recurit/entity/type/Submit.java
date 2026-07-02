package com.recruit.module.recurit.entity.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;

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
            throw new CustomException(
                    ResponseCode.VALIDATION_ERROR,
                    "submit",
                    "입력 가능값: Y, N, 입력된 값: " + value
            );
        }
    }
}
