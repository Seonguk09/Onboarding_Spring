package com.midasin.onboarding.service;

import com.midasin.onboarding.common.config.exception.CustomException;
import com.midasin.onboarding.common.config.exception.ErrorCode;
import com.midasin.onboarding.domain.JobPosting;
import com.midasin.onboarding.domain.User;
import com.midasin.onboarding.dto.JobPostingCreateRq;
import com.midasin.onboarding.dto.JobPostingDetailRs;
import com.midasin.onboarding.dto.JobPostingListRs;
import com.midasin.onboarding.repository.JobPostingRepository;
import com.midasin.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    @Transactional(readOnly = true)
    public List<JobPostingListRs> getJobPostingList() {
        return jobPostingRepository.findAll().stream().map(jp -> new JobPostingListRs(jp.getJobPostingId(), jp.getTitle(), jp.getPositionName(), jp.getEmploymentType(), jp.getLocation(), jp.getDepartment(), jp.getStatusType(), jp.getSalary(), jp.getWorkType(), jp.getOpeningDatetime(), jp.getClosingDatetime())).toList();
    }

    @Transactional(readOnly = true)
    public JobPostingDetailRs getJobPosting(Integer id) {
        JobPosting jp = jobPostingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));

        List<String> techStacks = jp.getJobPostingTechStacks().stream().map(jpt -> jpt.getTechStack().getName()).toList();
        List<String> educations = jp.getJobPostingEducations().stream().map(jpe -> jpe.getEducation().getSchool()).toList();
        List<String> careers = jp.getJobPostingCareers().stream().map(jpc -> jpc.getCareer().getCompany()).toList();

        return new JobPostingDetailRs(jp.getJobPostingId(), jp.getTitle(), jp.getDescription(), jp.getPositionName(), jp.getEmploymentType(), jp.getLocation(), jp.getDepartment(), jp.getQuantity(), jp.getQualification(), jp.getRequiredCompetency(), jp.getPreference(), jp.getStatusType(), jp.getSalary(), jp.getWorkType(), jp.getOpeningDatetime(), jp.getClosingDatetime(), jp.getCreatedDatetime(), jp.getModifiedDatetime(), techStacks, educations, careers);
    }

    @Transactional
    public void deleteJobPosting(Integer id) {
        JobPosting jobPosting = jobPostingRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        jobPostingRepository.delete(jobPosting);
    }

    private JobPosting createJobPosting(JobPostingCreateRq rq) {
        return JobPosting.builder().title(rq.title()).description(rq.description()).positionName(rq.positionName()).employmentType(rq.employmentType()).location(rq.location()).department(rq.department()).quantity(rq.quantity()).qualification(rq.qualification()).requiredCompetency(rq.requiredCompetency()).preference(rq.preference()).salary(rq.salary()).workType(rq.workType()).build();
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
