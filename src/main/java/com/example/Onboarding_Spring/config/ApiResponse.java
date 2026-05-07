package com.example.Onboarding_Spring.config;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
    private final int code;
    private final boolean success;
    private final String message;
    private final T data;

    public ApiResponse(int code, boolean success, String message, T data) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(int code, String message, T data) {
        return new ApiResponse<>(code, true, message, data);
    }

    public static <T> ApiResponse<T> failure(int code, String message) {
        return new ApiResponse<>(code, false, message, null);
    }
}
