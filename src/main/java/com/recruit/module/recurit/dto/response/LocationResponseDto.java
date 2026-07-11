package com.recruit.module.recurit.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Getter
@AllArgsConstructor
public class LocationResponseDto {
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "일련번호", example = "1", type = "string")
    private Long locSeq;

    @Schema(description = "이름", example = "서울")
    private String locName;
}
