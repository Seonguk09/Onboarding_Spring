package com.midasin.onboarding.service;

import com.midasin.onboarding.common.config.exception.CustomException;
import com.midasin.onboarding.common.config.exception.ErrorCode;
import com.midasin.onboarding.domain.User;
import com.midasin.onboarding.domain.enums.RoleType;
import com.midasin.onboarding.dto.AdminSignUpRq;
import com.midasin.onboarding.dto.SignUpRq;
import com.midasin.onboarding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.company.key}")
    private String companyKey;

    // 어드민 회원가입 기능
    @Transactional
    public void adminSignUp(AdminSignUpRq adminSignUpRq) {
        if (!companyKey.equals(adminSignUpRq.companyKey())) {
            throw new CustomException(ErrorCode.INVALID_COMPANY_KEY);
        }
        isEmailDuplicate(adminSignUpRq.email());
        User user = User.of(adminSignUpRq.email(), passwordEncoder.encode(adminSignUpRq.password()), adminSignUpRq.name(), RoleType.ADMIN);
        userRepository.save(user);
    }

    // 회원가입 기능
    @Transactional
    public void signUp(SignUpRq signUpRq) {
        isEmailDuplicate(signUpRq.email());
        User user = User.of(signUpRq.email(), passwordEncoder.encode(signUpRq.password()), signUpRq.name(), RoleType.USER);
        userRepository.save(user);
    }

    // 중복된 email 체크
    private void isEmailDuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }
}
