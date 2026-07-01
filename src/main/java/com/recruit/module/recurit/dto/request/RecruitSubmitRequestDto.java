package com.recruit.module.recurit.dto.request;

import com.recruit.module.recurit.entity.type.Gender;
import com.recruit.module.recurit.entity.type.Submit;
import com.recruit.module.recurit.entity.type.WorkType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitSubmitRequestDto {
    @NotNull(message = "생년월일을 입력해주세요.")
    private LocalDate birth;

    @NotNull(message = "성별을 입력해주세요.")
    private Gender gender;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotNull(message = "희망지역을 입력해주세요.")
    private Long locSeq;

    @NotNull(message = "희망직무 입력해주세요.")
    private WorkType workType;

    @NotNull(message = "제출여부를 선택해주세요.")
    private Submit submit;

    @Valid
    private List<EducationRequestDto> educationList;

    @Valid
    private List<CareerRequestDto> careerList;

    @Valid
    private List<CertificateRequestDto> certificateList;
}
