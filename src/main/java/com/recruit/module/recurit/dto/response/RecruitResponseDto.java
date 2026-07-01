package com.recruit.module.recurit.dto.response;

import com.recruit.module.recurit.entity.type.Gender;
import com.recruit.module.recurit.entity.type.WorkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitResponseDto {
    private Long recSeq;
    private String name;
    private LocalDate birth = null;
    private Gender gender = null;
    private String phone;
    private String email = null;
    private String address = null;
    private Long locSeq = null;
    private WorkType workType = null;
    private String submit;
}
