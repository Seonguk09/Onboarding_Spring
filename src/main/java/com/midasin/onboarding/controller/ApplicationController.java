package com.midasin.onboarding.controller;

import com.midasin.onboarding.common.ApiResponse;
import com.midasin.onboarding.dto.ApplicationApplyRq;
import com.midasin.onboarding.dto.ApplicationDetailRs;
import com.midasin.onboarding.dto.ApplicationListRs;
import com.midasin.onboarding.dto.ApplicationStatusChangeRq;
import com.midasin.onboarding.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping(value = "/application/upload/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<String> uploadApplicationFile(@PathVariable Integer id, @RequestPart("file") MultipartFile file) {
        applicationService.uploadApplicationFile(id, file);
        return ApiResponse.success(200, "파일 업로드 성공", null);
    }

    // 전체 페이지 조회이기 때문에 admin u
    @GetMapping("/admin/applications")
    public ApiResponse<List<ApplicationListRs>> getApplicationsWithPaging(@RequestParam int jobPostingId, @RequestParam int page, @RequestParam(defaultValue = "10") int size) {
        return ApiResponse.success(200, "지원서 목록 조회 성공", applicationService.getApplicationsByJobPostingId(jobPostingId, page, size));
    }

    @GetMapping("application/{id}")
    public ApiResponse<ApplicationDetailRs> getApplicationDetail(@PathVariable Integer id) {
        return ApiResponse.success(200, "지원서 상세 조회 성공", applicationService.getApplicationById(id));
    }
}
