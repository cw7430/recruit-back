package com.recruit.module.auth.dto.response;

public record LoginResponseDto(
        String accessToken,
        Long accessTokenExpiresAtMs,
        String refreshToken,
        Long refreshTokenExpiresAtMs
) {
}
