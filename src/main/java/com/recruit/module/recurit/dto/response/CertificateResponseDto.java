package com.recruit.module.recurit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CertificateResponseDto {
    @Schema(description = "일련번호", example = "1")
    private Long certSeq;

    @Schema(description = "자격증명", example = "정보처리기사")
    private String qualifyName;

    @Schema(description = "취득일", example = "2024-01-01")
    private LocalDate acquDate;

    @Schema(description = "발행기관명", example = "한국산업인력공단")
    private String organizeName;
}
