package com.midasin.Onboarding_Spring.controller;

import com.midasin.Onboarding_Spring.common.ApiResponse;
import com.midasin.Onboarding_Spring.dto.AdminSignUpRq;
import com.midasin.Onboarding_Spring.dto.SignUpRq;
import com.midasin.Onboarding_Spring.service.UserService;
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
    public ApiResponse<String> signUp(@RequestBody SignUpRq signUpDto) {
        userService.signUp(signUpDto);
        return ApiResponse.success(201, "회원가입 성공", null);
    }

    // 어드민 회원가입 API
    @PostMapping("/admin/signUp")
    public ApiResponse<String> adminSignUp(@RequestBody AdminSignUpRq adminSignUpRq) {
        userService.adminSignUp(adminSignUpRq);
        return ApiResponse.success(201, "어드민 회원가입 성공", null);
    }
}
