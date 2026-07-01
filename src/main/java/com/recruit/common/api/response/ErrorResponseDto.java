package com.recruit.common.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

public sealed abstract class ErrorResponseDto extends ResponseDto
        permits ErrorResponseDto.Simple, ErrorResponseDto.WithErrors {

    @Getter
    @AllArgsConstructor
    public static final class Simple extends ErrorResponseDto {
        private final String code;
        private final String message;
    }

    @Getter
    @AllArgsConstructor
    public static final class WithErrors<T> extends ErrorResponseDto {
        private final String code;
        private final String message;
        private final T errors;
    }
}
