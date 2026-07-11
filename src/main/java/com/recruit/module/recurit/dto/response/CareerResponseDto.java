package com.recruit.module.recurit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CareerResponseDto {
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "일련번호", example = "1", type = "string")
    private Long carSeq;

    @Schema(description = "회사명", example = "(주)익명")
    private String compName;

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "지역 일련번호", example = "1", type = "string")
    private Long locSeq;

    @Schema(description = "근무 시작일", example = "2024-01-01")
    private LocalDate startPeriod;

    @Schema(description = "근무 종료일", example = "2025-01-01")
    private LocalDate endPeriod;

    @Schema(description = "직무", example = "팀장")
    private String task;
}
