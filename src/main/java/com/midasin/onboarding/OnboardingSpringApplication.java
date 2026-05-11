package com.midasin.onboarding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OnboardingSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboardingSpringApplication.class, args);
	}

}
