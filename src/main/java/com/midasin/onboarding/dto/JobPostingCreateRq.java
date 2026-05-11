package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.EmploymentType;
import com.midasin.onboarding.domain.enums.WorkType;

public record JobPostingCreateRq(String title, String description, String positionName, EmploymentType employmentType, String location, String department, String quantity, String qualification, String requiredCompetency, String preference, String salary, WorkType workType) {
}
