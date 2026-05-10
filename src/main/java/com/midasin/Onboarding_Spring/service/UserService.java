package com.midasin.Onboarding_Spring.service;

import com.midasin.Onboarding_Spring.common.config.exception.CustomException;
import com.midasin.Onboarding_Spring.common.config.exception.ErrorCode;
import com.midasin.Onboarding_Spring.domain.User;
import com.midasin.Onboarding_Spring.dto.SignUpDto;
import com.midasin.Onboarding_Spring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입 기능
    @Transactional
    public void signUp(SignUpDto signUpDto) {
        isEmailDuplicate(signUpDto.email());
        User user = createUserEntity(signUpDto);
        userRepository.save(user);
    }

    // 중복된 email 체크
    private void isEmailDuplicate(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.DUPLICATED_EMAIL);
        }
    }

    // User 엔티티 생성
    private User createUserEntity(SignUpDto signUpDto) {
        return User.builder()
                .email(signUpDto.email())
                .password(passwordEncoder.encode(signUpDto.password()))
                .name(signUpDto.name())
                .build();
    }
}
