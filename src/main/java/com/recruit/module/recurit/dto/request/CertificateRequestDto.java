package com.recruit.module.recurit.dto.request;

import com.recruit.module.recurit.dto.vo.CertificateVo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateRequestDto {
    @Schema(description = "일련번호", example = "1", nullable = true)
    private String certSeq = null;

    @NotBlank(message = "자격증명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "자격증명 형식이 올바르지 않습니다.")
    @Schema(description = "자격증명", example = "정보처리기사")
    private String qualifyName;

    @PastOrPresent(message = "취득일은 미래 날짜일 수 없습니다.")
    @Schema(description = "취득일", example = "2024-01-01")
    private LocalDate acquDate;

    @NotBlank(message = "발행기관명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "발행기관명 형식이 올바르지 않습니다.")
    @Schema(description = "발행기관명", example = "한국산업인력공단")
    private String organizeName;

    public static List<CertificateVo> toVoList(List<CertificateRequestDto> certificateList) {
        return certificateList.stream().map(certificate -> new CertificateVo(
                certificate.getCertSeq() == null ? null : Long.parseLong(certificate.getCertSeq()),
                certificate.getQualifyName(),
                certificate.getAcquDate(),
                certificate.getOrganizeName()
        )).toList();
    }
}
