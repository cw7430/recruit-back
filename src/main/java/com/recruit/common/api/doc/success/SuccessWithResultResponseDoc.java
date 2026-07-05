package com.recruit.common.api.doc.success;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class SuccessWithResultResponseDoc<T> extends SuccessResponseDoc {
    public SuccessWithResultResponseDoc(T result){
        super();
        this.result = result;
    }

    @Schema(description = "응답 결과")
    private final T result;
}
