package com.example.Onboarding_Spring.config;

public record ApiResponse<T>(int code, boolean success, String message, T data) {

    public static <T> ApiResponse<T> success(int code, String message, T data) {
        return new ApiResponse<>(code, true, message, data);
    }

    public static <T> ApiResponse<T> failure(int code, String message) {
        return new ApiResponse<>(code, false, message, null);
    }
}
