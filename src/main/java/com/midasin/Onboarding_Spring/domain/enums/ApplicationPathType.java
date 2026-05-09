package com.midasin.Onboarding_Spring.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationPathType {
    HOMEPAGE("홈페이지"), SARAMIN("사람인"), JOBKOREA("잡코리아"), LINKEDIN("링크드인"), OTHER("기타");

    private final String description;
}
