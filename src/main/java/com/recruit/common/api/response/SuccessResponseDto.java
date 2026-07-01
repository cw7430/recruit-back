package com.recruit.common.api.response;

import com.recruit.common.api.type.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

public sealed abstract class SuccessResponseDto extends ResponseDto
        permits SuccessResponseDto.Simple, SuccessResponseDto.WithResult {

    @Getter
    @AllArgsConstructor
    private static final class Simple extends SuccessResponseDto {
        private final String code = ResponseCode.SUCCESS.getCode();
        private final String message = ResponseCode.SUCCESS.getMessage();
    }


    @Getter
    @AllArgsConstructor
    private static final class WithResult<T> extends SuccessResponseDto {
        private final String code = ResponseCode.SUCCESS.getCode();
        private final String message = ResponseCode.SUCCESS.getMessage();
        private final T result;
    }

    public static SuccessResponseDto ok() {
        return new Simple();
    }

    public static <T> SuccessResponseDto okWith(T result) {
        return new WithResult<>(result);
    }
}
