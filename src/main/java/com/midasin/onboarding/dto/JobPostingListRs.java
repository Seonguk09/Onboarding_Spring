package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.EmploymentType;
import com.midasin.onboarding.domain.enums.StatusType;
import com.midasin.onboarding.domain.enums.WorkType;

import java.time.LocalDateTime;

public record JobPostingListRs(
        Integer jobPostingId,
        String title,
        String positionName,
        EmploymentType employmentType,
        String location,
        String department,
        StatusType statusType,
        String salary,
        WorkType workType,
        LocalDateTime openingDatetime,
        LocalDateTime closingDatetime
) {
}
