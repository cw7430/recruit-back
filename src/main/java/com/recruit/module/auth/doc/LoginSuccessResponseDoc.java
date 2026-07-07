package com.recruit.module.auth.doc;

import com.recruit.common.api.doc.success.SuccessWithResultResponseDoc;
import com.recruit.module.auth.dto.response.LoginResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LoginSuccessResponse")
public class LoginSuccessResponseDoc extends SuccessWithResultResponseDoc<LoginResponseDto> {
    public LoginSuccessResponseDoc(LoginResponseDto result) {
        super(result);
    }
}
