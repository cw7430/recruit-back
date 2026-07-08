package com.recruit.module.recurit.dto.request;

import com.recruit.module.recurit.entity.type.Gender;
import com.recruit.module.recurit.entity.type.Submit;
import com.recruit.module.recurit.entity.type.WorkType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @Schema(description = "생년월일", example = "1996-01-01")
    private LocalDate birth;

    @NotNull(message = "성별을 입력해주세요.")
    @Schema(description = "성별", example = "M")
    private Gender gender;

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Schema(description = "이메일", example = "example@example.com")
    private String email;

    @NotBlank(message = "주소를 입력해주세요.")
    @Schema(description = "거주지", example = "서울특별시 용산구")
    private String address;

    @NotNull(message = "희망지역을 입력해주세요.")
    @Schema(description = "주소 일련번호", example = "1")
    private Long locSeq;

    @NotNull(message = "희망직무 입력해주세요.")
    @Schema(description = "희망 직무", example = "FULL_TIME")
    private WorkType workType;

    @NotNull(message = "제출여부를 선택해주세요.")
    @Schema(description = "제출 여부", example = "N")
    private Submit submit;

    @Valid
    @NotEmpty(message = "학력사항은 최소한 1개 이상 들어가야 합니다.")
    @Schema(description = "학력 사항")
    private List<EducationRequestDto> educationList;

    @Valid
    @Schema(description = "경력 사항", nullable = true)
    private List<CareerRequestDto> careerList;

    @Valid
    @Schema(description = "자격증 사항", nullable = true)
    private List<CertificateRequestDto> certificateList;
}
