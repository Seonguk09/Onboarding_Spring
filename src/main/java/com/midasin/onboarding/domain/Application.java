package com.midasin.onboarding.domain;

import com.midasin.onboarding.domain.enums.ApplicationPathType;
import com.midasin.onboarding.domain.enums.ApplicationStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
    public void addEducation(Education education) {
        education.setApplication(this);
        this.educations.add(education);
    }

    public void removeEducation(Education education) {
        this.educations.remove(education);
        education.setApplication(null);
    }

    public void addCareer(Career career) {
        career.setApplication(this);
        this.careers.add(career);
    }

    public void removeCareer(Career career) {
        this.careers.remove(career);
        career.setApplication(null);
    }

    public void addTechStack(TechStack techStack) {
        ApplicationTechStack applicationTechStack = new ApplicationTechStack();
        applicationTechStack.setApplication(this);
        applicationTechStack.setTechStack(techStack);
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

    public static Application of(String contact, String file, String portfolio, ApplicationPathType applicationPathType, JobPosting jobPosting, User createdUser) {
        Application application = new Application();
        application.contact = contact;
        application.file = file;
        application.portfolio = portfolio;
        application.applyDatetime = LocalDateTime.now();
        application.applicationPathType = applicationPathType;
        application.statusType = ApplicationStatusType.APPLIED;
        application.changeJobPosting(jobPosting);
        application.changeCreatedUser(createdUser);
        return application;
    }

}
