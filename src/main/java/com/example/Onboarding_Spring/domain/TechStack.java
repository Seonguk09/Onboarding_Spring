package com.example.Onboarding_Spring.domain;

import com.example.Onboarding_Spring.domain.enums.ProficiencyType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tech_stack")
@Getter @Setter
public class TechStack {

    @Id @GeneratedValue
    private Integer techStackId;

    @Column(length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ProficiencyType proficiencyType;
}

