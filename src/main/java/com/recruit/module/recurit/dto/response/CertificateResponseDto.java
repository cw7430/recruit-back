package com.recruit.module.recurit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CertificateResponseDto {
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "일련번호", example = "1", type = "string")
    private Long certSeq;

    @Schema(description = "자격증명", example = "정보처리기사")
    private String qualifyName;

    @Schema(description = "취득일", example = "2024-01-01")
    private LocalDate acquDate;

    @Schema(description = "발행기관명", example = "한국산업인력공단")
    private String organizeName;
}
