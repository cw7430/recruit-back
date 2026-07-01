package com.recruit.module.recurit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CertificateRequestDto {
    private Long certSeq = null;

    @NotBlank(message = "자격증명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "자격증명 형식이 올바르지 않습니다.")
    private String qualifyName;

    @PastOrPresent(message = "취득일은 미래 날짜일 수 없습니다.")
    private LocalDate acquDate;

    @NotBlank(message = "발행기관명을 입력해주세요.")
    @Pattern(regexp = "^[가-힣a-zA-Z0-9\\s-]+$", message = "발행기관명 형식이 올바르지 않습니다.")
    private String organizeName;
}
