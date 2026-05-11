package com.midasin.onboarding.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkType {
    ONSITE("온사이트"), REMOTE("원격"), HYBRID("하이브리드");
    private final String description;
}

