package com.midasin.onboarding.dto;

import com.midasin.onboarding.domain.enums.ApplicationStatusType;
import jakarta.validation.constraints.NotNull;

public record ApplicationStatusChangeRq(@NotNull ApplicationStatusType statusType) {}
