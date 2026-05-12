package com.midasin.onboarding.service;

import com.midasin.onboarding.common.config.exception.CustomException;
import com.midasin.onboarding.common.config.exception.ErrorCode;
import com.midasin.onboarding.domain.*;
import com.midasin.onboarding.dto.ApplicationApplyRq;
import com.midasin.onboarding.dto.ApplicationDetailRs;
import com.midasin.onboarding.dto.ApplicationListRs;
import com.midasin.onboarding.dto.ApplicationStatusChangeRq;
import com.midasin.onboarding.repository.ApplicationRepository;
import com.midasin.onboarding.repository.JobPostingRepository;
import com.midasin.onboarding.repository.TechStackRepository;
import com.midasin.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final UserRepository userRepository;
    private final TechStackRepository techStackRepository;

    @Value("${file.dir}")
    private String fileDir;

    @Transactional
    public void applyForJobPosting(ApplicationApplyRq rq) {
        JobPosting jobPosting = jobPostingRepository.findById(rq.jobPostingId()).orElseThrow(() -> new CustomException(ErrorCode.JOB_NOT_FOUND));
        User user = getCurrentUser();

        Application application = Application.of(rq.contact(), rq.portfolio(), rq.applicationPathType(), jobPosting, user);

        if (rq.educations() != null) {
            for (ApplicationApplyRq.EducationRq educationRq : rq.educations()) {
                application.addEducation(educationRq.school(), educationRq.major(), educationRq.startDatetime(), educationRq.endDatetime(), educationRq.currentType());
            }
        }

        if (rq.careers() != null) {
            for (ApplicationApplyRq.CareerRq careerRq : rq.careers()) {
                application.addCareer(careerRq.company(), careerRq.role(), careerRq.team(), careerRq.position(), careerRq.startDate(), careerRq.endDate(), careerRq.currentYn(), careerRq.description());
            }
        }

        if (rq.techStacks() != null) {
            for (ApplicationApplyRq.TechStackRq techStackRq : rq.techStacks()) {
                TechStack techStack = techStackRepository.findByName(techStackRq.name()).orElseGet(() -> techStackRepository.save(TechStack.of(techStackRq.name(), techStackRq.proficiencyType())));
                ApplicationTechStack applicationTechStack = ApplicationTechStack.of(application, techStack);
                application.addApplicationTechStack(applicationTechStack);
            }
        }

        applicationRepository.save(application);
    }

    @Transactional
    public void changeApplicationStatus(Integer applicationId, ApplicationStatusChangeRq rq) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));

        User user = getCurrentUser();

        application.updateStatus(rq.statusType(), user);
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Transactional
    public void uploadApplicationFile(Integer applicationId, MultipartFile file) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));
        if (file.isEmpty()) {
            throw new CustomException(ErrorCode.FILE_NOT_FOUND);
        }
        String savedName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path saveDir = Paths.get(fileDir);
        Path savePath = saveDir.resolve(savedName);

        try {
            Files.createDirectories(saveDir);
            Files.copy(file.getInputStream(), savePath);
        } catch (IOException e) {
            throw new RuntimeException("파일 저장 실패", e);
        }

        application.uploadFile(savePath.toString());
    }

    @Transactional(readOnly = true)
    public List<ApplicationListRs> getApplicationsByJobPostingId(Integer jobPostingId, int page, int size) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        return applicationRepository.findAllByJobPosting_JobPostingId(jobPostingId, pageable).stream().map(ApplicationListRs::from).toList();
    }

    @Transactional(readOnly = true)
    public ApplicationDetailRs getApplicationById(Integer applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(() -> new CustomException(ErrorCode.APPLICATION_NOT_FOUND));

        return new ApplicationDetailRs(application.getJobPosting().getJobPostingId(), application.getContact(), application.getFile(), application.getPortfolio(), application.getApplicationPathType(),
                application.getEducations().stream().map(e -> new ApplicationDetailRs.EducationDetailRs(e.getSchool(), e.getMajor(), e.getStartDatetime(), e.getEndDatetime(), e.getCurrentType())).toList(),
                application.getCareers().stream().map(c -> new ApplicationDetailRs.CareerDetailRs(c.getCompany(), c.getRole(), c.getTeam(), c.getPosition(), c.getStartDate(), c.getEndDate(), c.getCurrentYn(), c.getDescription())).toList(),
                application.getApplicationTechStacks().stream().map(t -> new ApplicationDetailRs.ApplicationTechStackDetailRs(t.getTechStack().getName(), t.getTechStack().getProficiencyType())).toList());
    }
}
