package com.recruit.module.recurit.dto.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CertificateVo implements SequentialVo {
    private Long certSeq;
    private String qualifyName;
    private LocalDate acquDate;
    private String organizeName;

    @Override
    public Long getSequence() {
        return 0L;
    }
}
