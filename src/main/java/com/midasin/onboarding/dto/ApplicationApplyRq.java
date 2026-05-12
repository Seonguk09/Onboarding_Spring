package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.ApplicationPathType;
import com.midasin.onboarding.domain.enums.CurrentType;
import com.midasin.onboarding.domain.enums.ProficiencyType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record ApplicationApplyRq(@NotNull Integer jobPostingId, String contact, String portfolio,
                                 ApplicationPathType applicationPathType, List<EducationRq> educations,
                                 List<CareerRq> careers, List<TechStackRq> techStacks) {
    public record EducationRq(String school, String major, LocalDateTime startDatetime, LocalDateTime endDatetime,
                              CurrentType currentType) {
    }

    public record CareerRq(String company, String role, String team, String position, LocalDate startDate,
                           LocalDate endDate, Boolean currentYn, String description) {
    }

    public record TechStackRq(String name, ProficiencyType proficiencyType) {
    }
}
