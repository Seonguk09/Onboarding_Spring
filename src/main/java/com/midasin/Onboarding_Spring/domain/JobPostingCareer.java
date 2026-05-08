package com.midasin.Onboarding_Spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "job_posting_career")
@Getter @Setter
public class JobPostingCareer {

    @Id @GeneratedValue
    private Integer jobPostingCareerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id")
    private Career career;
}

