package com.midasin.onboarding.service;

import com.midasin.onboarding.domain.JobPosting;
import com.midasin.onboarding.dto.JobPostingCreateRq;
import com.midasin.onboarding.repository.JobPostingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;

    @Transactional
    public void saveJobPosting(JobPostingCreateRq jobPostingCreateRq) {
        JobPosting jobPosting = JobPosting.builder()
                .title(jobPostingCreateRq.title())
                .description(jobPostingCreateRq.description())
                .positionName(jobPostingCreateRq.positionName())
                .employmentType(jobPostingCreateRq.employmentType())
                .location(jobPostingCreateRq.location())
                .department(jobPostingCreateRq.department())
                .quantity(jobPostingCreateRq.quantity())
                .qualification(jobPostingCreateRq.qualification())
                .requiredCompetency(jobPostingCreateRq.requiredCompetency())
                .preference(jobPostingCreateRq.preference())
                .salary(jobPostingCreateRq.salary())
                .workType(jobPostingCreateRq.workType())
                .build();
        jobPostingRepository.save(jobPosting);
    }
}
