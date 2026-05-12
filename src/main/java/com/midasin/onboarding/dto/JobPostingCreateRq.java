package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.EmploymentType;
import com.midasin.onboarding.domain.enums.WorkType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record JobPostingCreateRq(@NotBlank String title, String description, String positionName,
                                 @NotNull EmploymentType employmentType, String location, String department,
                                 String quantity, String qualification, String requiredCompetency, String preference,
                                 String salary, @NotNull WorkType workType, LocalDateTime closingDatetime) {
}
