package uz.company.hrms.dto.auth;

public record LoginRequest(
        String fullName,
        String email,
        String password
) {}
