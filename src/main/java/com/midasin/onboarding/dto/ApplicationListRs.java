package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.Application;
import com.midasin.onboarding.domain.enums.ApplicationPathType;
import com.midasin.onboarding.domain.enums.ApplicationStatusType;

import java.time.LocalDateTime;

public record ApplicationListRs(Integer applicationId, String applicantName, String contact,
                                ApplicationStatusType statusType, LocalDateTime applyDatetime,
                                ApplicationPathType applicationPathType) {

    public static ApplicationListRs from(Application application) {
        return new ApplicationListRs(
                application.getApplicationId(),
                application.getCreatedUser().getName(),
                application.getContact(),
                application.getStatusType(),
                application.getApplyDatetime(),
                application.getApplicationPathType()
        );
    }

}
