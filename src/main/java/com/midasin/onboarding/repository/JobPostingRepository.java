package com.midasin.onboarding.repository;

import com.midasin.onboarding.domain.JobPosting;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {
    Page<JobPosting> findAll(Pageable pageable);
}
