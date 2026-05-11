package com.midasin.onboarding.service;

import com.midasin.onboarding.common.config.exception.CustomException;
import com.midasin.onboarding.common.config.exception.ErrorCode;
import com.midasin.onboarding.domain.JobPosting;
import com.midasin.onboarding.domain.User;
import com.midasin.onboarding.dto.JobPostingCreateRq;
import com.midasin.onboarding.repository.JobPostingRepository;
import com.midasin.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JobPostingService {

    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveJobPosting(JobPostingCreateRq rq) {
        JobPosting jobPosting = createJobPosting(rq);
        jobPosting.changeCreateUser(getCurrentUser());
        jobPostingRepository.save(jobPosting);
    }

    @Transactional
    public void updateJobPosting(Integer id, JobPostingCreateRq rq) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));

        jobPosting.setTitle(rq.title());
        jobPosting.setDescription(rq.description());
        jobPosting.setPositionName(rq.positionName());
        jobPosting.setEmploymentType(rq.employmentType());
        jobPosting.setLocation(rq.location());
        jobPosting.setDepartment(rq.department());
        jobPosting.setQuantity(rq.quantity());
        jobPosting.setQualification(rq.qualification());
        jobPosting.setRequiredCompetency(rq.requiredCompetency());
        jobPosting.setPreference(rq.preference());
        jobPosting.setSalary(rq.salary());
        jobPosting.setWorkType(rq.workType());
        jobPosting.setModifiedUser(getCurrentUser());
    }

    @Transactional
    public void deleteJobPosting(Integer id) {
        JobPosting jobPosting = jobPostingRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        jobPostingRepository.delete(jobPosting);
    }

    private JobPosting createJobPosting(JobPostingCreateRq rq) {
        return JobPosting.builder()
                .title(rq.title())
                .description(rq.description())
                .positionName(rq.positionName())
                .employmentType(rq.employmentType())
                .location(rq.location())
                .department(rq.department())
                .quantity(rq.quantity())
                .qualification(rq.qualification())
                .requiredCompetency(rq.requiredCompetency())
                .preference(rq.preference())
                .salary(rq.salary())
                .workType(rq.workType())
                .build();
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
