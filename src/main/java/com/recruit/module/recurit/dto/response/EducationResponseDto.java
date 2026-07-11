package com.recruit.module.recurit.dto.response;

import com.recruit.module.recurit.entity.type.Division;
import com.recruit.module.recurit.entity.type.SchoolType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EducationResponseDto {
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "일련번호", example = "1", type = "string")
    private Long eduSeq;

    @Schema(description = "이름", example = "익명대학교")
    private String schoolName;

    @Schema(description = "학종", example = "BACHELOR")
    private SchoolType schoolType;

    @Schema(description = "학력 구분", example = "GRADUATED")
    private Division division;

    @Schema(description = "입학일", example = "2024-01-01")
    private LocalDate startPeriod;

    @Schema(description = "졸업일", example = "2025-01-01")
    private LocalDate endPeriod;

    @Schema(description = "전공", example = "역사학과")
    private String major;

    @Schema(description = "학점", example = "4.5")
    private BigDecimal grade;

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "지역 일련번호", example = "1", type = "string")
    private Long locSeq;
}
