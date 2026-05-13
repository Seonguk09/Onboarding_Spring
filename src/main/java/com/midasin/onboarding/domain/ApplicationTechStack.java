package com.midasin.onboarding.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "application_tech_stack")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationTechStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applicationTechStackId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id")
    private Application application;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tech_stack_id")
    private TechStack techStack;

    static public ApplicationTechStack of(Application application, TechStack techStack) {
        ApplicationTechStack applicationTechStack = new ApplicationTechStack();
        applicationTechStack.application = application;
        applicationTechStack.techStack = techStack;
        return applicationTechStack;
    }
}

