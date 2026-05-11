package com.midasin.onboarding.controller;

import com.midasin.onboarding.common.ApiResponse;
import com.midasin.onboarding.dto.JobPostingCreateRq;
import com.midasin.onboarding.service.JobPostingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @PostMapping("/admin/jobPosting")
    public ApiResponse<String> createJobPosting(@Valid @RequestBody JobPostingCreateRq jobPostingCreateRq) {
        jobPostingService.saveJobPosting(jobPostingCreateRq);
        return ApiResponse.success(201, "채용 공고 등록 성공", null);
    }

    @PutMapping("/admin/jobPosting/{id}")
    public ApiResponse<String> updateJobPosting(@PathVariable("id") Integer id, @RequestBody @Valid JobPostingCreateRq jobPostingCreateRq) {
        jobPostingService.updateJobPosting(id, jobPostingCreateRq);
        return ApiResponse.success(200, "채용 공고 수정 성공", null);
    }

    @DeleteMapping("/admin/jobPosting/{id}")
    public ApiResponse<String> deleteJobPosting(@PathVariable("id") Integer id) {
        jobPostingService.deleteJobPosting(id);
        return ApiResponse.success(200, "채용 공고 삭제 성공", null);
    }
}
