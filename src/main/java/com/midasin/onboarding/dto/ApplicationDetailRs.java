package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.ApplicationPathType;
import com.midasin.onboarding.domain.enums.CurrentType;
import com.midasin.onboarding.domain.enums.ProficiencyType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ApplicationDetailRs(Integer jobPostingId, String contact, String file, String portfolio,
                                  ApplicationPathType applicationPathType, List<EducationDetailRs> educations,
                                  List<CareerDetailRs> careers, List<ApplicationTechStackDetailRs> techStacks) {

    public record EducationDetailRs(String school, String major, LocalDateTime startDatetime, LocalDateTime endDatetime,
                                    CurrentType currentType) {
    }

    public record CareerDetailRs(String company, String role, String team, String position, LocalDate startDate,
                                 LocalDate endDate, Boolean currentYn, String description) {
    }

    public record ApplicationTechStackDetailRs(String name, ProficiencyType proficiencyType) {
    }
}
