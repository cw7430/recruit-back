package com.recruit.module.recurit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LocationResponseDto {
    @Schema(description = "일련번호", example = "1")
    private Long locSeq;

    @Schema(description = "이름", example = "서울")
    private String locName;
}
