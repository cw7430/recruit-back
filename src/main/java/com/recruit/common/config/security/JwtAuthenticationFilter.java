package com.recruit.common.config.security;

import com.recruit.common.config.security.type.Role;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = jwtUtil.extractTokenForFilter(request);

            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            boolean isRefreshPath = "/api/v1/auth/refresh".equals((request.getRequestURI()));
            Claims claims = jwtProvider.parseClaims(token, isRefreshPath);

            if (claims != null) {
                if (isRefreshPath) {
                    request.setAttribute("refreshClaims", claims);
                } else {
                    setAuthentication(claims);
                }
            } else {
                request.setAttribute("exception", "INVALID_TOKEN");
            }

        } catch (Exception e) {
            request.setAttribute("exception", e);
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(Claims claims) {
        String id = claims.getSubject();
        String roleCode = claims.get("role", String.class);
        Role role = Role.from(roleCode);
        List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role.getAuthority()));
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(id, null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
