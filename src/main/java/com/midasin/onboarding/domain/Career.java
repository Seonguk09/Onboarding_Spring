package com.midasin.onboarding.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "career")
@Getter
@Setter
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
}

