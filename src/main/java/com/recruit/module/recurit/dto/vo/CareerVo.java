package com.recruit.module.recurit.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@AllArgsConstructor
public class CareerVo implements PeriodVo {
    private @Getter Long carSeq;
    private @Getter String compName;
    private @Getter Long locSeq;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private @Getter String task;

    @Override
    public Long getSequence() {
        return carSeq;
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
