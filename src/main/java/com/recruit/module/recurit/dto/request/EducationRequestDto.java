package com.recruit.module.recurit.dto.request;

import com.recruit.module.recurit.dto.vo.EducationVo;
import com.recruit.module.recurit.entity.type.Division;
import com.recruit.module.recurit.entity.type.SchoolType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EducationRequestDto {
    @Schema(description = "일련번호", example = "1", nullable = true)
    private String eduSeq = null;

    @NotBlank(message = "학교명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "학교명 형식이 올바르지 않습니다.")
    @Schema(description = "이름", example = "익명대학교")
    private String schoolName;

    @NotNull(message = "학종을 입력해주세요.")
    @Schema(description = "학종", example = "BACHELOR")
    private SchoolType schoolType;

    @NotBlank(message = "학력 구분을 입력해주세요.")
    @Pattern(regexp = "^[가-힣]+$", message = "학력 구분 형식이 올바르지 않습니다.")
    @Schema(description = "학력 구분", example = "GRADUATED")
    private Division division;

    @NotNull(message = "입학일을 입력해주세요.")
    @PastOrPresent(message = "입학일은 미래 날짜일 수 없습니다.")
    @Schema(description = "입학일", example = "2024-01-01")
    private LocalDate startPeriod;

    @NotNull(message = "졸업일을 입력해주세요.")
    @Schema(description = "졸업일", example = "2025-01-01")
    private LocalDate endPeriod;

    @NotBlank(message = "전공을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "전공 형식이 올바르지 않습니다.")
    @Schema(description = "전공", example = "역사학과")
    private String major;

    @NotNull(message = "학점을 입력해주세요.")
    @DecimalMin(value = "0.00", message = "학점은 0점 이상이어야 합니다.")
    @DecimalMax(value = "4.50", message = "학점은 4.5점을 넘을 수 없습니다.")
    @Schema(description = "학점", example = "4.5")
    private BigDecimal grade;

    @NotNull(message = "학교 지역을 입력해주세요.")
    @Schema(description = "지역 일련번호", example = "1")
    private String locSeq;

    @AssertTrue(message = "입학일은 졸업일보다 빨라야 합니다.")
    public boolean isPeriodValid() {
        if (startPeriod == null || endPeriod == null) {
            return true;
        }
        return startPeriod.isBefore(endPeriod) || startPeriod.isEqual(endPeriod);
    }

    public static List<EducationVo> toVoList(List<EducationRequestDto> educationList) {
        return educationList.stream().map(education -> new EducationVo(
                education.getEduSeq() == null ? null : Long.parseLong(education.getEduSeq()),
                education.getSchoolName(),
                education.getSchoolType(),
                education.getDivision(),
                education.getStartPeriod(),
                education.getEndPeriod(),
                education.getMajor(),
                education.getGrade(),
                Long.parseLong(education.getLocSeq())
        )).toList();
    }
}
