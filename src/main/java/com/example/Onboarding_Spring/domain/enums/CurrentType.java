package com.example.Onboarding_Spring.domain.enums;

import com.example.Onboarding_Spring.domain.JobPosting;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrentType {
    ENROLLED("재학"),
    GRADUATED("졸업"),
    EXPECTED("졸업 예정");

    private final String description;
}

