package com.midasin.Onboarding_Spring.domain;

import com.midasin.Onboarding_Spring.domain.enums.ApplicationPathType;
import com.midasin.Onboarding_Spring.domain.enums.ApplicationStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "application")
@Getter @Setter
public class Application {

    @Id @GeneratedValue
    private Integer applicationId;

    @Column(length = 255)
    private String contact;

    @Column(length = 255)
    private String file;

    @Column(length = 255)
    private String portfolio;

    private LocalDateTime applyDatetime;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private ApplicationPathType applicationPathType;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ApplicationStatusType statusType;

    private LocalDateTime statusModifyDatetime;

    private LocalDateTime createdDatetime;

    private LocalDateTime modifiedDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_id")
    private User createUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_user_id")
    private User modifyUser;
}
