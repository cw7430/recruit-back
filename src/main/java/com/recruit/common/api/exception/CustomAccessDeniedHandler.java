package com.recruit.common.api.exception;

import com.recruit.common.api.response.ErrorResponseDto;
import com.recruit.common.api.response.ResponseDto;
import com.recruit.common.api.type.ResponseCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull AccessDeniedException accessDeniedException
    ) throws IOException, ServletException {
        ResponseCode responseCode = ResponseCode.FORBIDDEN;

        ResponseDto errorResponse = ErrorResponseDto.from(
                responseCode
        );
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(responseCode.getStatus().value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
