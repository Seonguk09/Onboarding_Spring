package com.midasin.onboarding.domain;

import com.midasin.onboarding.domain.enums.EmploymentType;
import com.midasin.onboarding.domain.enums.StatusType;
import com.midasin.onboarding.domain.enums.WorkType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "job_posting")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    @CreatedDate
    private LocalDateTime createdDatetime;

    @LastModifiedDate
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

    // 연관관계 편의 메서드 구현
    // 기본 setter 아니기 때문에 메소드명 change로 시작
    public void changeCreateUser(User createdUser) {
        this.createdUser = createdUser;
        if (!createdUser.getJobPostings().contains(this)) {
            createdUser.getJobPostings().add(this);
        }
    }
}
