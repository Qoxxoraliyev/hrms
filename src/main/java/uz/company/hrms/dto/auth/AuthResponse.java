package uz.company.hrms.dto.auth;

public record AuthResponse(
        String accessToken,
        String refreshToken
){}
