package com.midasin.onboarding.common.config.exception;

import com.midasin.onboarding.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ApiResponse<String> handleCustomException(CustomException ex) {
        return ApiResponse.failure(ex.getErrorCode().getStatus(), ex.getErrorCode().getMessage());
    }
}
