package com.recruit.module.recurit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecruitCalculateResponseDto {
    @Schema(description = "학력사항", example = "대학교 졸업", nullable = true)
    private String eduInfo;

    @Schema(description = "경력사항", example = "경력 3년 6개월", nullable = true)
    private String carInfo;

    @Schema(description = "희망 연봉", example = "회사 내규에 따름", nullable = true)
    private String hopeSal;

    @Schema(description = "희망 근무지", example = "서울 전체", nullable = true)
    private String hopeLoc;

    @Schema(description = "근무 형태", example = "정규직", nullable = true)
    private String workType;
}
