package com.recruit.common.api.exeption;

import com.recruit.common.api.response.ErrorResponseDto;
import com.recruit.common.api.response.ResponseDto;
import com.recruit.common.api.type.ResponseCode;
import com.recruit.common.api.type.ValidationError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleValidationException(MethodArgumentNotValidException ex) {
        List<ValidationError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(f -> new ValidationError(f.getField(), f.getDefaultMessage())).toList();

        log.warn("Validation failed: {}", errors);

        return new ResponseEntity<>(
                ErrorResponseDto.of(
                        ResponseCode.VALIDATION_ERROR,
                        errors
                ),
                ResponseCode.VALIDATION_ERROR.getStatus()
        );
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDto> handleCustomException(CustomException ex) {
        log.error("Custom exception occurred: {}", ex.getResponseCode().getMessage());

        return new ResponseEntity<>(
                ErrorResponseDto.from(
                        ex.getResponseCode()
                ),
                ex.getResponseCode().getStatus()
        );
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ResponseDto> handleDatabaseException(DataAccessException ex) {
        log.error("DataAccess exception occurred: {}", ex.getMessage());

        return new ResponseEntity<>(
                ErrorResponseDto.from(
                        ResponseCode.INTERNAL_SERVER_ERROR
                ),
                ResponseCode.INTERNAL_SERVER_ERROR.getStatus()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleGeneralException(Exception ex) {
        log.error("General exception occurred: {}", ex.getMessage());

        return new ResponseEntity<>(
                ErrorResponseDto.from(
                        ResponseCode.INTERNAL_SERVER_ERROR
                ),
                ResponseCode.INTERNAL_SERVER_ERROR.getStatus()
        );
    }
}
