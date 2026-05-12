package com.midasin.onboarding.controller;

import com.midasin.onboarding.common.ApiResponse;
import com.midasin.onboarding.dto.ApplicationApplyRq;
import com.midasin.onboarding.dto.ApplicationStatusChangeRq;
import com.midasin.onboarding.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ApplicationController {
    private final ApplicationService applicationService;

    @PostMapping("/application")
    public ApiResponse<String> applyForJobPosting(@RequestBody @Valid ApplicationApplyRq rq) {
        applicationService.applyForJobPosting(rq);
        return ApiResponse.success(201, "지원 성공", null);
    }

    @PatchMapping("/application/{id}")
    public ApiResponse<String> changeApplicationStatus(@PathVariable Integer id, @RequestBody @Valid ApplicationStatusChangeRq rq) {
        applicationService.changeApplicationStatus(id, rq);
        return ApiResponse.success(200, "지원 상태 변경 성공", null);
    }

}
