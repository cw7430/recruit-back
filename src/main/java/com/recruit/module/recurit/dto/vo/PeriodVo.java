package com.recruit.module.recurit.dto.vo;

import java.time.LocalDate;

public interface PeriodVo extends SequentialVo {
    LocalDate getStartPeriod();
    LocalDate getEndPeriod();
}
