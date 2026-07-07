package com.recruit.module.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(name = "LoginRequest")
public record LoginRequestDto(
        @NotBlank(message = "이름을 입력해주세요.")
        @Pattern(regexp = "^[가-힣]+$", message = "이름 형식이 올바르지 않습니다.")
        @Schema(description = "이름", example = "홍길동")
        String name,

        @NotBlank(message = "휴대전화 번호를 입력해주세요.")
        @Pattern(regexp = "^(010|011|016|017|018|019)-\\d{3,4}-\\d{4}$", message = "휴대전화번호 형식이 올바르지 않습니다.")
        @Schema(description = "휴대전화번호", example = "010-0000-0000")
        String phone,

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Schema(description = "비빌번호", example = "examplepw1234!@")
        String password
) {
}
