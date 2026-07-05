package com.recruit.common.config.security;

import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
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

    public Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null
                || !authentication.isAuthenticated()
                || authentication instanceof AnonymousAuthenticationToken) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        return Long.parseLong(authentication.getName());
    }

    public Long extractUserIdFromRefresh(HttpServletRequest request) {
        Claims claims = (Claims) request.getAttribute("refreshClaims");

        if (claims == null) {
            throw new CustomException(ResponseCode.UNAUTHORIZED);
        }

        return Long.parseLong(claims.getSubject());
    }
}
