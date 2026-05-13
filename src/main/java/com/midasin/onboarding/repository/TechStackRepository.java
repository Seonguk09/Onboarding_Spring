package com.midasin.onboarding.repository;

import com.midasin.onboarding.domain.TechStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TechStackRepository extends JpaRepository<TechStack, Integer> {
    Optional<TechStack> findByName(String name);
}
