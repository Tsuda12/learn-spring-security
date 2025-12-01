package dev.tsuda.spring_security.dto.response;

public record RegisterUserResponse(
        String name,
        String email
) {}
