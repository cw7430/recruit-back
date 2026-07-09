package com.recruit.module.recurit.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CareerVo implements SequentialVo {
    private Long carSeq;
    private String compName;
    private Long locSeq;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private String task;

    @Override
    public Long getSequence() {
        return carSeq;
    }
}
