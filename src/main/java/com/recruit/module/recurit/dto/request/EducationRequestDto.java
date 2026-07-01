package com.recruit.module.recurit.dto.request;

import com.recruit.module.recurit.entity.type.Division;
import com.recruit.module.recurit.entity.type.SchoolType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequestDto {
    private Long eduSeq = null;

    @NotBlank(message = "학교명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "학교명 형식이 올바르지 않습니다.")
    private String schoolName;

    @NotNull(message = "학종을 입력해주세요.")
    private SchoolType schoolType;

    @NotBlank(message = "학력 구분을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]+$", message = "학력 구분 형식이 올바르지 않습니다.")
    private Division division;

    @NotNull(message = "입학일을 입력해주세요.")
    @PastOrPresent(message = "입학일은 미래 날짜일 수 없습니다.")
    private LocalDate startPeriod;

    @NotNull(message = "졸업일을 입력해주세요.")
    private LocalDate endPeriod;

    @NotBlank(message = "전공을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "전공 형식이 올바르지 않습니다.")
    private String major;

    @NotNull(message = "학점을 입력해주세요.")
    @DecimalMin(value = "0.00", message = "학점은 0점 이상이어야 합니다.")
    @DecimalMax(value = "4.50", message = "학점은 4.5점을 넘을 수 없습니다.")
    private BigDecimal grade;

    @NotBlank(message = "학교 지역을 입력해주세요.")
    private Long locSeq;

    @AssertTrue(message = "입학일은 졸업일보다 빨라야 합니다.")
    public boolean isPeriodValid() {
        if (startPeriod == null || endPeriod == null) {
            return true;
        }
        return startPeriod.isBefore(endPeriod) || startPeriod.isEqual(endPeriod);
    }
}
