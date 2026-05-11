package com.midasin.Onboarding_Spring.common.config.security;

import com.midasin.Onboarding_Spring.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public record CustomUserDetails(User user) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRoleType().name()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // 이메일을 username 처럼 사용
    @Override
    public String getUsername() {
        return user.getEmail();
    }

}
