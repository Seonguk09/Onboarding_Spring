package com.midasin.onboarding.domain;

import com.midasin.onboarding.domain.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private RoleType roleType;

    @CreatedDate
    private LocalDateTime createdDatetime;

    @LastModifiedDate
    private LocalDateTime modifiedDatetime;

    @OneToMany(mappedBy = "createUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

    @OneToMany(mappedBy = "createdUser", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobPosting> jobPostings = new ArrayList<>();
}
