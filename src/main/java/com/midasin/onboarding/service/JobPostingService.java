package com.midasin.onboarding.service;

import com.midasin.onboarding.common.config.exception.CustomException;
import com.midasin.onboarding.common.config.exception.ErrorCode;
import com.midasin.onboarding.domain.JobPosting;
import com.midasin.onboarding.domain.User;
import com.midasin.onboarding.dto.JobPostingCreateRq;
import com.midasin.onboarding.dto.JobPostingDetailRs;
import com.midasin.onboarding.dto.JobPostingListRs;
import com.midasin.onboarding.dto.JobPostingStatusChangeRq;
import com.midasin.onboarding.repository.JobPostingRepository;
import com.midasin.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        JobPosting jobPosting = jobPostingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        jobPosting.update(rq.title(), rq.description(), rq.positionName(), rq.employmentType(), rq.location(), rq.department(), rq.quantity(), rq.qualification(), rq.requiredCompetency(), rq.preference(), rq.salary(), rq.workType(), rq.closingDatetime());
        jobPosting.changeModifiedUser(getCurrentUser());
    }

    @Transactional(readOnly = true)
    public List<JobPostingListRs> getJobPostingList() {
        return jobPostingRepository.findAll().stream().map(jp -> new JobPostingListRs(jp.getJobPostingId(), jp.getTitle(), jp.getPositionName(), jp.getEmploymentType(), jp.getLocation(), jp.getDepartment(), jp.getStatusType(), jp.getSalary(), jp.getWorkType(), jp.getOpeningDatetime(), jp.getClosingDatetime())).toList();
    }

    @Transactional(readOnly = true)
    public JobPostingDetailRs getJobPosting(Integer id) {
        JobPosting jp = jobPostingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));

        return new JobPostingDetailRs(jp.getJobPostingId(), jp.getTitle(), jp.getDescription(), jp.getPositionName(), jp.getEmploymentType(), jp.getLocation(), jp.getDepartment(), jp.getQuantity(), jp.getQualification(), jp.getRequiredCompetency(), jp.getPreference(), jp.getStatusType(), jp.getSalary(), jp.getWorkType(), jp.getOpeningDatetime(), jp.getClosingDatetime(), jp.getCreatedDatetime(), jp.getModifiedDatetime());
    }

    @Transactional(readOnly = true)
    public Page<JobPosting> getJobPostingWithPaging(int page, int size){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return jobPostingRepository.findAll(pageable);
    }

    @Transactional
    public void deleteJobPosting(Integer id) {
        JobPosting jobPosting = jobPostingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        jobPostingRepository.delete(jobPosting);
    }

    @Transactional
    public void changeJobPostingStatus(Integer id, JobPostingStatusChangeRq rq) {
        JobPosting jobPosting = jobPostingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        jobPosting.updateStatus(rq.statusType());
        jobPosting.changeModifiedUser(getCurrentUser());
    }

    private JobPosting createJobPosting(JobPostingCreateRq rq) {
        return JobPosting.of(rq.title(), rq.description(), rq.positionName(), rq.employmentType(), rq.location(), rq.department(), rq.quantity(), rq.qualification(), rq.requiredCompetency(), rq.preference(), rq.salary(), rq.workType(), rq.closingDatetime());
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
