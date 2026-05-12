package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.StatusType;
import jakarta.validation.constraints.NotNull;

public record JobPostingStatusChangeRq(@NotNull StatusType statusType) {
}
