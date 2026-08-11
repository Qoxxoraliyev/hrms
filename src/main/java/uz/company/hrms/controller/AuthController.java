package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.auth.AuthResponse;
import uz.company.hrms.dto.auth.LoginRequest;
import uz.company.hrms.dto.auth.RefreshTokenRequest;
import uz.company.hrms.security.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody LoginRequest request){
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request){
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshTokenRequest request) {
        return authService.refresh(request.refreshToken());
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        authService.logout(token);
    }

}
