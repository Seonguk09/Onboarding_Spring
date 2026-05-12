package com.midasin.onboarding.service;

import com.midasin.onboarding.common.config.exception.CustomException;
import com.midasin.onboarding.common.config.exception.ErrorCode;
import com.midasin.onboarding.domain.*;
import com.midasin.onboarding.dto.ApplicationApplyRq;
import com.midasin.onboarding.repository.ApplicationRepository;
import com.midasin.onboarding.repository.JobPostingRepository;
import com.midasin.onboarding.repository.TechStackRepository;
import com.midasin.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;
    private final TechStackRepository techStackRepository;

    @Transactional
    public void applyForJobPosting(ApplicationApplyRq rq) {
        JobPosting jobPosting = jobPostingRepository.findById(rq.jobPostingId()).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        User user = getCurrentUser();

        Application application = Application.of(rq.contact(), rq.file(), rq.portfolio(), rq.applicationPathType(), jobPosting, user);

        if (rq.educations() != null) {
            for (ApplicationApplyRq.EducationRq educationRq : rq.educations()) {
                Education education = new Education();
                education.setSchool(educationRq.school());
                education.setMajor(educationRq.major());
                education.setStartDatetime(educationRq.startDatetime());
                education.setEndDatetime(educationRq.endDatetime());
                education.setCurrentType(educationRq.currentType());
                application.addEducation(education);
            }
        }

        if (rq.careers() != null) {
            for (ApplicationApplyRq.CareerRq careerRq : rq.careers()) {
                Career career = new Career();
                career.setCompany(careerRq.company());
                career.setRole(careerRq.role());
                career.setTeam(careerRq.team());
                career.setPosition(careerRq.position());
                career.setStartDate(careerRq.startDate());
                career.setEndDate(careerRq.endDate());
                career.setCurrentYn(careerRq.currentYn());
                career.setDescription(careerRq.description());
                application.addCareer(career);
            }
        }

        if (rq.techStacks() != null) {
            for (ApplicationApplyRq.TechStackRq techStackRq : rq.techStacks()) {
                TechStack techStack = new TechStack();
                techStack.setName(techStackRq.name());
                techStack.setProficiencyType(techStackRq.proficiencyType());
                techStackRepository.save(techStack);
                application.addTechStack(techStack);
            }
        }

        applicationRepository.save(application);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
