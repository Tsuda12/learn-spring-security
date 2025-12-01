package dev.tsuda.spring_security.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "E-mail is required!")
        String email,

        @NotBlank(message = "Passowrd is required!")
        String password
) {}
