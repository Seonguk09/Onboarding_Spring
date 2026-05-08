package com.midasin.Onboarding_Spring.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationStatusType {
    APPLIED("지원 완료"),
    REVIEWING("검토 진행 중"),
    PASSED("합격"),
    REJECTED("불합격");

    private final String description;
}
