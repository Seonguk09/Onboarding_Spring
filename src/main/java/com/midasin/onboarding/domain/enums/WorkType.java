package com.midasin.onboarding.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WorkType {
    FULL_TIME("풀타임"), PART_TIME("파트타임"), INTERN("인턴");

    private final String description;
}

