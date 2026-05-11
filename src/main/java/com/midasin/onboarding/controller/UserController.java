package com.midasin.onboarding.controller;

import com.midasin.onboarding.common.ApiResponse;
import com.midasin.onboarding.dto.AdminSignUpRq;
import com.midasin.onboarding.dto.SignUpRq;
import com.midasin.onboarding.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입 API
    @PostMapping("/signUp")
    public ApiResponse<String> signUp(@Valid @RequestBody SignUpRq signUpRq) {
        userService.signUp(signUpRq);
        return ApiResponse.success(201, "회원가입 성공", null);
    }

    // 어드민 회원가입 API
    @PostMapping("/admin/signUp")
    public ApiResponse<String> adminSignUp(@Valid @RequestBody AdminSignUpRq adminSignUpRq) {
        userService.adminSignUp(adminSignUpRq);
        return ApiResponse.success(201, "어드민 회원가입 성공", null);
    }
}
