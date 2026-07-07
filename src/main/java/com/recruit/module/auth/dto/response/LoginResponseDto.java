package com.recruit.module.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponseDto(
        @Schema(description = "Access Token")
        String accessToken,
        @Schema(description = "Access Token 만료시간")
        Long accessTokenExpiresAtMs,
        @Schema(description = "Refresh Token")
        String refreshToken,
        @Schema(description = "Refresh Token 만료시간")
        Long refreshTokenExpiresAtMs
) {
}
