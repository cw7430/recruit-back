package com.recruit.common.api.doc.success;

import com.recruit.common.api.type.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(name = "SuccessResponse")
@Getter
public class SuccessResponseDoc {
    @Schema(example = "SU")
    private final String code = ResponseCode.SUCCESS.getCode();
    @Schema(example = "요청이 성공적으로 처리되었습니다.")
    private final String message = ResponseCode.SUCCESS.getMessage();
}
