package com.midasin.Onboarding_Spring.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProficiencyType {
    HIGHEST("최상"), HIGH("상"), MIDDLE("중"), LOW("하"), LOWEST("최하");

    private final String description;
}

