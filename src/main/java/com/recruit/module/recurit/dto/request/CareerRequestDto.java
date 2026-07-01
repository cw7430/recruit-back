package com.recruit.module.recurit.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CareerRequestDto {
    private Long carSeq = null;

    @NotBlank(message = "회사명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "학교명 형식이 올바르지 않습니다.")
    private String compName;

    @NotNull(message = "회사 지역을 입력해주세요.")
    private Long locSeq;

    @NotNull(message = "근무 시작일을 입력해주세요.")
    @PastOrPresent(message = "시작일은 미래 날짜일 수 없습니다.")
    private LocalDate startPeriod;

    @NotNull(message = "근무 종료일을 입력해주세요.")
    private LocalDate endPeriod;

    @NotBlank(message = "직무를 입력해주세요.")
    private String task;

    @AssertTrue(message = "시작일은 종료일보다 빨라야 합니다.")
    public boolean isPeriodValid() {
        if (startPeriod == null || endPeriod == null) {
            return true;
        }
        return startPeriod.isBefore(endPeriod) || startPeriod.isEqual(endPeriod);
    }
}
