package com.midasin.Onboarding_Spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_posting_education")
@Getter
@Setter
public class JobPostingEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostingEducationId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "education_id")
    private Education education;
}

