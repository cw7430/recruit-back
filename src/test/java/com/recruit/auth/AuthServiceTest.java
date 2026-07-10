package com.recruit.auth;

import com.recruit.BaseIntegrationTest;
import com.recruit.common.api.exception.CustomException;
import com.recruit.module.auth.AuthService;
import com.recruit.module.auth.dto.request.LoginRequestDto;
import com.recruit.module.auth.dto.response.LoginResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AuthServiceTest extends BaseIntegrationTest {
    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("최초 지원자는 자동 생성된다")
    public void createUser() {
        LoginRequestDto data = new LoginRequestDto(
                "테스트",
                "010-0000-0000",
                "password1234!"
        );

        LoginResponseDto resDto = authService.login(data);

        assertThat(resDto.accessToken()).isNotBlank();
        assertThat(resDto.refreshToken()).isNotBlank();
    }

    @Test
    @DisplayName("기존 지원자는 로그인된다")
    public void login() {
        LoginRequestDto data = new LoginRequestDto(
                "테스트",
                "010-0000-0000",
                "password1234!"
        );

        authService.login(data);

        LoginRequestDto correctData = new LoginRequestDto(
                "테스트",
                "010-0000-0000",
                "password1234!"
        );

        LoginResponseDto resDto = authService.login(correctData);

        assertThat(resDto.accessToken()).isNotBlank();
        assertThat(resDto.refreshToken()).isNotBlank();
    }

    @Test
    @DisplayName("기존 지원자의 비밀번호가 틀리면 예외가 발생한다")
    public void loginFailWithWrongPassword() {
        LoginRequestDto data = new LoginRequestDto(
                "테스트",
                "010-0000-0000",
                "password1234!"
        );
        authService.login(data);

        LoginRequestDto wrongData = new LoginRequestDto(
                "테스트",
                "010-0000-0000",
                "password1234"
        );

        assertThatThrownBy(() -> authService.login(wrongData))
                .isInstanceOf(CustomException.class);
    }
}
