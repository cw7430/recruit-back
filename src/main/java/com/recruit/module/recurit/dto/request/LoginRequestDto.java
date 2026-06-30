package com.recruit.module.recurit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequestDto(
        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]+$", message = "이름 형식이 올바르지 않습니다.")
        String name,

        @NotBlank(message = "휴대전화 번호를 입력해주세요.")
        @Pattern(regexp = "^(010|011|016|017|018|019)-\\d{3,4}-\\d{4}$", message = "휴대전화번호 형식이 올바르지 않습니다.")
        String phone,

        @NotBlank(message = "패스워드를 입력해주세요.")
        String password
) {
}
