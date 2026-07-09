package com.recruit.module.recurit.dto.vo;

import com.recruit.module.recurit.entity.type.Division;
import com.recruit.module.recurit.entity.type.SchoolType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class EducationVo implements SequentialVo {
    private Long eduSeq;
    private String schoolName;
    private SchoolType schoolType;
    private Division division;
    private LocalDate startPeriod;
    private LocalDate endPeriod;
    private String major;
    private BigDecimal grade;
    private Long locSeq;

    @Override
    public Long getSequence() {
        return eduSeq;
    }
}
