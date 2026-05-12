package com.midasin.onboarding.controller;

import com.midasin.onboarding.common.ApiResponse;
import com.midasin.onboarding.dto.JobPostingCreateRq;
import com.midasin.onboarding.dto.JobPostingDetailRs;
import com.midasin.onboarding.dto.JobPostingListRs;
import com.midasin.onboarding.dto.JobPostingStatusChangeRq;
import com.midasin.onboarding.service.JobPostingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class JobPostingController {
    private final JobPostingService jobPostingService;

    @GetMapping("/jobPosting")
    public ApiResponse<List<JobPostingListRs>> getJobPostings() {
        return ApiResponse.success(200, "채용 공고 목록 조회 성공", jobPostingService.getJobPostingList());
    }

    @GetMapping("/jobPosting/{id}")
    public ApiResponse<JobPostingDetailRs> getJobPosting(@PathVariable Integer id) {
        return ApiResponse.success(200, "채용 공고 상세 조회 성공", jobPostingService.getJobPosting(id));
    }

    @PostMapping("/admin/jobPosting")
    public ApiResponse<String> createJobPosting(@Valid @RequestBody JobPostingCreateRq jobPostingCreateRq) {
        jobPostingService.saveJobPosting(jobPostingCreateRq);
        return ApiResponse.success(201, "채용 공고 등록 성공", null);
    }

    @PutMapping("/admin/jobPosting/{id}")
    public ApiResponse<String> updateJobPosting(@PathVariable Integer id, @RequestBody @Valid JobPostingCreateRq jobPostingCreateRq) {
        jobPostingService.updateJobPosting(id, jobPostingCreateRq);
        return ApiResponse.success(200, "채용 공고 수정 성공", null);
    }

    @DeleteMapping("/admin/jobPosting/{id}")
    public ApiResponse<String> deleteJobPosting(@PathVariable Integer id) {
        jobPostingService.deleteJobPosting(id);
        return ApiResponse.success(200, "채용 공고 삭제 성공", null);
    }

    @PatchMapping("/admin/jobPosting/{id}/status")
    public ApiResponse<String> changeJobPostingStatus(@PathVariable Integer id, @Valid @RequestBody JobPostingStatusChangeRq jobPostingStatusChangeRq) {
        jobPostingService.changeJobPostingStatus(id, jobPostingStatusChangeRq);
        return ApiResponse.success(200, "채용 공고 상태 변경 성공", null);
    }
}