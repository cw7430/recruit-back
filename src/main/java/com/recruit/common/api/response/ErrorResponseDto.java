package com.recruit.common.api.response;

import com.recruit.common.api.type.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

public sealed abstract class ErrorResponseDto extends ResponseDto
        permits ErrorResponseDto.Simple, ErrorResponseDto.WithErrors {


    @Getter
    @AllArgsConstructor
    protected static final class Simple extends ErrorResponseDto {
        private final String code;
        private final String message;
    }

    @Getter
    @AllArgsConstructor
    protected static final class WithErrors<T> extends ErrorResponseDto {
        private final String code;
        private final String message;
        private final T errors;
    }

    public static ErrorResponseDto from(ResponseCode responseCode) {
        return new Simple(responseCode.getCode(), responseCode.getMessage());
    }

    public static <T> ErrorResponseDto of(ResponseCode responseCode, T errors) {
        return new WithErrors<>(responseCode.getCode(), responseCode.getMessage(), errors);
    }
}