package com.midasin.onboarding.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdminSignUpRq(@NotBlank @Email String email, @NotBlank String password, @NotBlank String name,
                            @NotBlank String companyKey) {
}
