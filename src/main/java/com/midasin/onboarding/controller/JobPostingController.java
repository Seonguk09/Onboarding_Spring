package com.midasin.onboarding.controller;

import com.midasin.onboarding.common.ApiResponse;
import com.midasin.onboarding.dto.JobPostingCreateRq;
import com.midasin.onboarding.service.JobPostingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @PostMapping("/admin/jobPosting")
    public ApiResponse<String> createJobPosting(@Valid @RequestBody JobPostingCreateRq jobPostingCreateRq) {
        jobPostingService.saveJobPosting(jobPostingCreateRq);
        return ApiResponse.success(201, "채용 공고 등록 성공", null);
    }
}
