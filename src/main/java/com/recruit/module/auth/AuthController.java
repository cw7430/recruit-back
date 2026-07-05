package com.recruit.module.auth;

import com.recruit.common.api.response.ResponseDto;
import com.recruit.common.api.response.SuccessResponseDto;
import com.recruit.module.auth.dto.request.LoginRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginRequestDto reqDto) {
        return ResponseEntity.ok(SuccessResponseDto.okWith(authService.login(reqDto)));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ResponseDto> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(SuccessResponseDto.okWith(authService.refresh(request)));
    }
}
