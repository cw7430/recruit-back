package com.recruit.module.recurit.doc;

import com.recruit.common.api.doc.success.SuccessWithResultResponseDoc;
import com.recruit.module.recurit.dto.response.RecruitResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "RecruitSuccessResponse")
public class RecruitSuccessResponseDoc extends SuccessWithResultResponseDoc<RecruitResponseDto> {
    public RecruitSuccessResponseDoc(RecruitResponseDto result) {
        super(result);
    }
}
