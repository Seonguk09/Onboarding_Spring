package com.midasin.onboarding.repository;

import com.midasin.onboarding.domain.Application;
import com.midasin.onboarding.domain.JobPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    Page<Application> findAllByJobPosting_JobPostingId(Integer jobPostingId, Pageable pageable);
}
