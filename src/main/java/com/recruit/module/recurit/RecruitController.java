package com.recruit.module.recurit;

import com.recruit.common.api.doc.error.ErrorResponseDoc;
import com.recruit.common.api.response.ResponseDto;
import com.recruit.common.api.response.SuccessResponseDto;
import com.recruit.module.recurit.doc.RecruitSuccessResponseDoc;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/recruit")
@RequiredArgsConstructor
@Tag(name = "Recruit Controller", description = "입사 지원 API")
public class RecruitController {
    private final RecruitService recruitService;

    @GetMapping
    @SecurityRequirement(name = "access-token")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "조회 성공", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = RecruitSuccessResponseDoc.class)
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
                    responseCode = "404", description = "존재 오류", content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponseDoc.ResourceNotFound.class)
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
    public ResponseEntity<ResponseDto> getRecruit() {
        return ResponseEntity.ok(SuccessResponseDto.okWith(recruitService.getRecruit()));
    }
}
