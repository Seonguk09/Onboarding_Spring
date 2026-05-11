package com.midasin.onboarding.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusType {
    OPEN("진헹중"), CLOSED("종료");

    private final String description;
}
