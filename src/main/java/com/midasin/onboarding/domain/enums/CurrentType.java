package com.midasin.onboarding.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrentType {
    ENROLLED("재학"), GRADUATED("졸업"), EXPECTED("졸업 예정");

    private final String description;
}

