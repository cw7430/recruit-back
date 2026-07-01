package com.recruit.common.api.response;

import com.recruit.common.api.type.ResponseCode;
import lombok.Getter;

public sealed abstract class SuccessResponseDto extends ResponseDto
        permits SuccessResponseDto.Simple, SuccessResponseDto.WithResult {

    public static final class Simple extends SuccessResponseDto {
        private Simple() {
        }

        @Override
        public String getCode() {
            return ResponseCode.SUCCESS.getCode();
        }

        @Override
        public String getMessage() {
            return ResponseCode.SUCCESS.getMessage();
        }
    }

    @Getter
    public static final class WithResult<T> extends SuccessResponseDto {
        private final T result;

        public WithResult(T result) {
            this.result = result;
        }

        @Override
        public String getCode() {
            return ResponseCode.SUCCESS.getCode();
        }

        @Override
        public String getMessage() {
            return ResponseCode.SUCCESS.getMessage();
        }
    }
}
