package com.recruit.module.recurit.dto.request;

import com.recruit.module.recurit.dto.vo.CareerVo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CareerRequestDto {
    @Schema(description = "일련번호", example = "1", nullable = true)
    private Long carSeq = null;

    @NotBlank(message = "회사명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "학교명 형식이 올바르지 않습니다.")
    @Schema(description = "회사명", example = "(주)익명")
    private String compName;

    @NotNull(message = "회사 지역을 입력해주세요.")
    @Schema(description = "지역 일련번호", example = "1")
    private Long locSeq;

    @NotNull(message = "근무 시작일을 입력해주세요.")
    @PastOrPresent(message = "시작일은 미래 날짜일 수 없습니다.")
    @Schema(description = "근무 시작일", example = "2024-01-01")
    private LocalDate startPeriod;

    @NotNull(message = "근무 종료일을 입력해주세요.")
    @Schema(description = "근무 종료일", example = "2025-01-01")
    private LocalDate endPeriod;

    @NotBlank(message = "직무를 입력해주세요.")
    @Schema(description = "직무", example = "팀장")
    private String task;

    @AssertTrue(message = "시작일은 종료일보다 빨라야 합니다.")
    public boolean isPeriodValid() {
        if (startPeriod == null || endPeriod == null) {
            return true;
        }
        return startPeriod.isBefore(endPeriod) || startPeriod.isEqual(endPeriod);
    }

    public static List<CareerVo> toVoList(List<CareerRequestDto> careerList) {
        return careerList.stream().map(career -> new CareerVo(
                career.getCarSeq(),
                career.getCompName(),
                career.getLocSeq(),
                career.getStartPeriod(),
                career.getEndPeriod(),
                career.getTask()
        )).toList();
    }
}
