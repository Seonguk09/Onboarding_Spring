package com.midasin.Onboarding_Spring.domain;

import com.midasin.Onboarding_Spring.domain.enums.EmploymentType;
import com.midasin.Onboarding_Spring.domain.enums.StatusType;
import com.midasin.Onboarding_Spring.domain.enums.WorkType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_posting")
@Getter
@Setter
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostingId;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 50)
    private String positionName;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private EmploymentType employmentType;

    @Column(length = 50)
    private String location;

    @Column(length = 50)
    private String department;

    @Column(length = 10)
    private String quantity;

    @Column(columnDefinition = "TEXT")
    private String qualification;

    @Column(columnDefinition = "TEXT")
    private String requiredCompetency;

    @Column(columnDefinition = "TEXT")
    private String preference;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_type", length = 10)
    private StatusType statusType;

    @Column(length = 50)
    private String salary;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private WorkType workType;

    private LocalDateTime createdDatetime;

    private LocalDateTime modifiedDatetime;

    private LocalDateTime openingDatetime;

    private LocalDateTime closingDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_user_id")
    private User createdUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modified_user_id")
    private User modifiedUser;

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPostingEducation> jobPostingEducations = new ArrayList<>();

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPostingCareer> jobPostingCareers = new ArrayList<>();

    @OneToMany(mappedBy = "jobPosting", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPostingTechStack> jobPostingTechStacks = new ArrayList<>();
}
