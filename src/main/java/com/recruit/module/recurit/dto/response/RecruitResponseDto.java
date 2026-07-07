package com.recruit.module.recurit.dto.response;

import com.recruit.module.recurit.entity.type.Gender;
import com.recruit.module.recurit.entity.type.WorkType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecruitResponseDto {
    @Schema(description = "일련번호", example = "1")
    private Long recSeq;

    @Schema(description = "이름", example = "홍길동")
    private String name;

    @Schema(description = "생년월일", example = "1996-01-01", nullable = true)
    private LocalDate birth = null;

    @Schema(description = "성별", example = "M", nullable = true)
    private Gender gender = null;

    @Schema(description = "휴대전화번호", example = "010-0000-0000")
    private String phone;

    @Schema(description = "이메일", example = "example@example.com", nullable = true)
    private String email = null;

    @Schema(description = "거주지", example = "서울특별시 용산구", nullable = true)
    private String address = null;

    @Schema(description = "주소 일련번호", example = "1", nullable = true)
    private Long locSeq = null;

    @Schema(description = "희망 직무", example = "FULL_TIME", nullable = true)
    private WorkType workType = null;

    @Schema(description = "학력 사항", nullable = true)
    private List<EducationResponseDto> educationList;

    @Schema(description = "경력 사항", nullable = true)
    private List<CareerResponseDto> careerList;

    @Schema(description = "자격증 사항", nullable = true)
    private List<CertificateResponseDto> certificateList;

    @Schema(description = "지역 리스트")
    private List<LocationResponseDto> locationList;
}
