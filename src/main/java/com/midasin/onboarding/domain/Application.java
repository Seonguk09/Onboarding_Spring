package com.midasin.onboarding.domain;

import com.midasin.onboarding.domain.enums.ApplicationPathType;
import com.midasin.onboarding.domain.enums.ApplicationStatusType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "application", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"job_posting_id", "create_user_id"})
}) // 지원서의 경우 동일한 구인공고에 대해 동일한 사용자가 여러 번 지원할 수 없도록 unique 제약 조건 추가
@Getter
@Setter
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
    private User createUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modify_user_id")
    private User modifyUser;

    // 연관관계 편의 메서드
    // 기본적인 setter가 아니기 때문에 메소드 이름을 change로 시작하도록 변경
    public void changeJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
        if (!jobPosting.getApplications().contains(this)) {
            jobPosting.getApplications().add(this);
        }
    }

    public void changeCreateUser(User createUser) {
        this.createUser = createUser;
        if (!createUser.getApplications().contains(this)) {
            createUser.getApplications().add(this);
        }
    }

}
