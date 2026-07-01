package com.recruit.common.api.response;

public sealed abstract class ResponseDto
        permits SuccessResponseDto, ErrorResponseDto {
    public abstract String getCode();

    public abstract String getMessage();
}
