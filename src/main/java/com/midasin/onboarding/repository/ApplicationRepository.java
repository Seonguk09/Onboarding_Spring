package com.midasin.onboarding.repository;

import com.midasin.onboarding.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

}
