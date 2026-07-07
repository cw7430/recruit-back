package com.recruit.module.auth;

import com.recruit.common.api.exception.CustomException;
import com.recruit.common.api.type.ResponseCode;
import com.recruit.common.config.security.JwtProvider;
import com.recruit.common.config.security.JwtUtil;
import com.recruit.common.config.security.type.Role;
import com.recruit.common.config.security.type.TokenResponseClaim;
import com.recruit.module.auth.dto.request.LoginRequestDto;
import com.recruit.module.auth.dto.response.LoginResponseDto;
import com.recruit.module.recurit.entity.Recruit;
import com.recruit.module.recurit.entity.type.Submit;
import com.recruit.module.recurit.repository.jpa.RecruitRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final RecruitRepository recruitRepository;
    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    private LoginResponseDto generateLoginInfo(Recruit recruit) {
        TokenResponseClaim accessInfo =
                jwtProvider.generateAccessToken(recruit.getRecSeq().toString(), Role.USER.getCode());
        TokenResponseClaim refreshInfo = jwtProvider.generateRefreshToken(recruit.getRecSeq().toString());
        return new LoginResponseDto(
                accessInfo.token(),
                accessInfo.expiresAtMs(),
                refreshInfo.token(),
                recruit.getRecSeq()
        );
    }

    @Transactional
    public LoginResponseDto login(LoginRequestDto reqDto) {
        Recruit recruit = recruitRepository.findByNameAndPhone(reqDto.name(), reqDto.phone())
                .orElse(null);

        if (recruit == null) {
            String passwordHash = passwordEncoder.encode(reqDto.password());
            Recruit createdRecruit = recruitRepository.save(
                    Recruit.create(reqDto.name(), reqDto.phone(), passwordHash)
            );
            log.info("Created By recSeq : {}", createdRecruit.getRecSeq());
            return generateLoginInfo(createdRecruit);
        }

        if (!passwordEncoder.matches(reqDto.password(), recruit.getPasswordHash())) {
            throw new CustomException(ResponseCode.PASSWORD_ERROR);
        }

        if ((Submit.Y.getValue()).equals(recruit.getSubmit().getValue())) {
            throw new CustomException(ResponseCode.CONFLICT);
        }

        log.info("Login By recSeq : {}", recruit.getRecSeq());

        return generateLoginInfo(recruit);
    }

    @Transactional
    public LoginResponseDto refresh(HttpServletRequest request) {
        String token = jwtUtil.extractToken(request);
        Long recSeq = jwtUtil.extractUserIdFromRefresh(token);
        Recruit recruit = recruitRepository.findById(recSeq).orElseThrow(
                () -> new CustomException(ResponseCode.UNAUTHORIZED)
        );
        log.info("Refresh By recSeq : {}", recruit.getRecSeq());

        return generateLoginInfo(recruit);
    }
}
