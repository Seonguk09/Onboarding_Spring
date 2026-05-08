package com.midasin.Onboarding_Spring.domain;

import com.midasin.Onboarding_Spring.domain.enums.CurrentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "education")
@Getter
@Setter
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
}

