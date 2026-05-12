package com.midasin.onboarding.domain;

import com.midasin.onboarding.domain.enums.ApplicationPathType;
import com.midasin.onboarding.domain.enums.ApplicationStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.midasin.onboarding.domain.enums.CurrentType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "application", uniqueConstraints = {@UniqueConstraint(columnNames = {"job_posting_id", "create_user_id"})})
// 지원서의 경우 동일한 구인공고에 대해 동일한 사용자가 여러 번 지원할 수 없도록 unique 제약 조건 추가
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @CreatedDate
    private LocalDateTime createdDatetime;

    private LocalDateTime modifiedDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_posting_id")
    private JobPosting jobPosting;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "create_user_id")
    private User createdUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_user_id")
    private User modifiedUser;

    // 지원서에 학력, 경력, 기술 스택 정보를 넣기 때문에 일대다 관계로 매핑
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Career> careers = new ArrayList<>();

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationTechStack> applicationTechStacks = new ArrayList<>();


    // 연관관계 편의 메서드
    // 기본적인 setter가 아니기 때문에 메소드 이름을 change로 시작하도록 변경
    public void changeJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
        if (!jobPosting.getApplications().contains(this)) {
            jobPosting.getApplications().add(this);
        }
    }

    public void changeCreatedUser(User createdUser) {
        this.createdUser = createdUser;
        if (!createdUser.getApplications().contains(this)) {
            createdUser.getApplications().add(this);
        }
    }

    // 연관관계 편의 메서드 - 학력, 경력, 기술 스택 추가/삭제
    public void addEducation(String school, String major, LocalDateTime startDatetime, LocalDateTime endDatetime, CurrentType currentType) {
        this.educations.add(Education.of(this, school, major, startDatetime, endDatetime, currentType));
    }

    public void removeEducation(Education education) {
        this.educations.remove(education);
    }

    public void addCareer(String company, String role, String team, String position, LocalDate startDate, LocalDate endDate, Boolean currentYn, String description) {
        this.careers.add(Career.of(this, company, role, team, position, startDate, endDate, currentYn, description));
    }

    public void removeCareer(Career career) {
        this.careers.remove(career);
    }

    public void addApplicationTechStack(ApplicationTechStack applicationTechStack) {
        this.applicationTechStacks.add(applicationTechStack);
    }

    public void removeTechStack(TechStack techStack) {
        for (ApplicationTechStack applicationTechStack : applicationTechStacks) {
            if (applicationTechStack.getTechStack().getTechStackId().equals(techStack.getTechStackId())) {
                applicationTechStacks.remove(applicationTechStack);
                break;
            }
        }
    }

    public static Application of(String contact, String portfolio, ApplicationPathType applicationPathType, JobPosting jobPosting, User createdUser) {
        Application application = new Application();
        application.contact = contact;
        application.portfolio = portfolio;
        application.applyDatetime = LocalDateTime.now();
        application.applicationPathType = applicationPathType;
        application.statusType = ApplicationStatusType.APPLIED;
        application.changeJobPosting(jobPosting);
        application.changeCreatedUser(createdUser);
        return application;
    }

    public void update(String contact, String portfolio, ApplicationPathType applicationPathType) {
        this.contact = contact;
        this.portfolio = portfolio;
        this.applicationPathType = applicationPathType;
        this.statusModifyDatetime = LocalDateTime.now();
    }

    public void updateStatus(ApplicationStatusType statusType, User modifiedUser) {
        this.statusType = statusType;
        this.statusModifyDatetime = LocalDateTime.now();
        this.modifiedUser = modifiedUser;
    }

    public void uploadFile(String file) {
        this.file = file;
        this.modifiedDatetime = LocalDateTime.now();
    }
}
