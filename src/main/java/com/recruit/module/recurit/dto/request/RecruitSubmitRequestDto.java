package com.recruit.module.recurit.dto.request;

import com.recruit.module.recurit.entity.type.Gender;
import com.recruit.module.recurit.entity.type.WorkType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitSubmitRequestDto {
    private LocalDate birth;
    private Gender gender;
    private String email;
    private String address;
    private Long locSeq;
    private WorkType workType;
    private String submit;

    private List<EducationRequestDto> educationList;
    private List<CareerRequestDto> careerList;
    private List<CertificateRequestDto> certificateList;
}
