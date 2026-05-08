package com.example.Onboarding_Spring.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmploymentType {
    FULL_TIME("정규직"),
    PART_TIME("파트타임"),
    CONTRACT("계약직"),
    INTERN("인턴"),
    FREELANCE("프리랜스");

    private final String description;
}
