package com.midasin.onboarding.domain;

import com.midasin.onboarding.domain.enums.ProficiencyType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tech_stack")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer techStackId;

    @Column(length = 50)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private ProficiencyType proficiencyType;

    public static TechStack of(String name, ProficiencyType proficiencyType) {
        TechStack techStack = new TechStack();
        techStack.name = name;
        techStack.proficiencyType = proficiencyType;
        return techStack;
    }
}

