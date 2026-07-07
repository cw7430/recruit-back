package com.recruit.module.auth;

import com.recruit.common.api.doc.error.ErrorResponseDoc;
import com.recruit.common.api.response.ResponseDto;
import com.recruit.common.api.response.SuccessResponseDto;
import com.recruit.module.auth.doc.LoginSuccessResponseDoc;
import com.recruit.module.auth.dto.request.LoginRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Auth Controller", description = "보안 API")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "로그인")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "로그인 성공", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginSuccessResponseDoc.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "400", description = "입력값 오류", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.BadRequest.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "401", description = "잘못된 비밀번호", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.PasswordError.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "407", description = "제출된 사용자", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.Conflict.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "500", description = "서버 오류", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.InternalServerError.class)
                    )
            }
            )
    })
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid LoginRequestDto reqDto) {
        return ResponseEntity.ok(SuccessResponseDto.okWith(authService.login(reqDto)));
    }

    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "재발급 성공", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = LoginSuccessResponseDoc.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "401", description = "인증 오류", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.Unauthorized.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "401", description = "만료된 토큰", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.ExpiredToken.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "401", description = "잘못된 토큰", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.InvalidToken.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "403", description = "권한 오류", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.Conflict.class)
                    )
            }
            ),
            @ApiResponse(
                    responseCode = "500", description = "서버 오류", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.InternalServerError.class)
                    )
            }
            )
    })
    public ResponseEntity<ResponseDto> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(SuccessResponseDto.okWith(authService.refresh(request)));
    }
}
