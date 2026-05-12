package com.midasin.onboarding.domain;

import com.midasin.onboarding.domain.enums.CurrentType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "education")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer educationId;

    @Column(length = 50)
    private String school;

    @Column(length = 50)
    private String major;

    private LocalDateTime startDatetime;

    private LocalDateTime endDatetime;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private CurrentType currentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public static Education of(Application application, String school, String major, LocalDateTime startDatetime, LocalDateTime endDatetime, CurrentType currentType) {
        Education education = new Education();
        education.application = application;
        education.school = school;
        education.major = major;
        education.startDatetime = startDatetime;
        education.endDatetime = endDatetime;
        education.currentType = currentType;
        return education;
    }
}

