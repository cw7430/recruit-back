package com.recruit.module.recurit.dto.vo;

import com.recruit.module.recurit.entity.type.Division;
import com.recruit.module.recurit.entity.type.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
public class EducationVo implements PeriodVo {
    private @Getter Long eduSeq;
    private @Getter String schoolName;
    private @Getter SchoolType schoolType;
    private @Getter Division division;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private @Getter String major;
    private @Getter BigDecimal grade;
    private @Getter Long locSeq;

    @Override
    public Long getSequence() {
        return eduSeq;
    }

    @Override
    public LocalDate getStartPeriod() {
        return startPeriod;
    }

    @Override
    public LocalDate getEndPeriod() {
        return endPeriod;
    }
}
