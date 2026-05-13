package com.midasin.onboarding.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "career")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer careerId;

    @Column(nullable = false, length = 50)
    private String company;

    @Column(length = 50)
    private String role;

    @Column(length = 50)
    private String team;

    @Column(length = 50)
    private String position;

    private LocalDate startDate;

    private LocalDate endDate;

    private Boolean currentYn;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    public static Career of(Application application, String company, String role, String team, String position, LocalDate startDate, LocalDate endDate, Boolean currentYn, String description) {
        Career career = new Career();
        career.application = application;
        career.company = company;
        career.role = role;
        career.team = team;
        career.position = position;
        career.startDate = startDate;
        career.endDate = endDate;
        career.currentYn = currentYn;
        career.description = description;
        return career;
    }
}

