package com.midasin.Onboarding_Spring.common.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404, "유저 정보 없음"), INVALID_PASSWORD(400, "비밀번호가 일치하지 않음"), UNAUTHORIZED(401, "권한이 없는 사용자"), JOB_NOT_FOUND(404, "존재하지 않는 공고입니다."), DUPLICATED_EMAIL(409, "이미 존재하는 이메일입니다."), INVALID_COMPANY_KEY(401, "유효하지 않은 회사 키입니다.");

    private final int status;
    private final String message;
}
