package com.recruit.common.config.security;

import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    public String extractTokenForFilter(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header == null || header.isBlank()) return null;

        if (!header.startsWith("Bearer ")) return null;

        return header.substring(7).trim();
    }

    public String extractToken(HttpServletRequest request) {
        String token = extractTokenForFilter(request);
        if (token == null || token.isBlank()) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }
        return token;
    }

    public Long extractUserIdFromAccessToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        return Long.parseLong(authentication.getName());
    }
}
