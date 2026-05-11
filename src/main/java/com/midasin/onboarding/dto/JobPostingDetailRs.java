package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.EmploymentType;
import com.midasin.onboarding.domain.enums.StatusType;
import com.midasin.onboarding.domain.enums.WorkType;

import java.time.LocalDateTime;
import java.util.List;

public record JobPostingDetailRs(
        Integer jobPostingId,
        String title,
        String description,
        String positionName,
        EmploymentType employmentType,
        String location,
        String department,
        String quantity,
        String qualification,
        String requiredCompetency,
        String preference,
        StatusType statusType,
        String salary,
        WorkType workType,
        LocalDateTime openingDatetime,
        LocalDateTime closingDatetime,
        LocalDateTime createdDatetime,
        LocalDateTime modifiedDatetime,
        List<String> techStacks,
        List<String> educations,
        List<String> careers
) {
}
