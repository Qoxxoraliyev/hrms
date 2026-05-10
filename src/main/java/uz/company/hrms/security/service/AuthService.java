package uz.company.hrms.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.company.hrms.dto.auth.AuthResponse;
import uz.company.hrms.dto.auth.LoginRequest;
import uz.company.hrms.entity.User;
import uz.company.hrms.enums.Role;
import uz.company.hrms.repository.UserRepository;
import uz.company.hrms.security.jwt.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final TokenBlacklistService blacklistService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, TokenBlacklistService blacklistService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.blacklistService = blacklistService;
    }

    // Ro'yhatdan o'tish
    public AuthResponse register(LoginRequest request){
        if (userRepository.existsByEmail(request.email())){
            throw new RuntimeException("Email already registered");
        }

        User user=new User();
        user.setFullName(request.fullName());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);
        userRepository.save(user);
        String access=jwtService.generateAccessToken(user.getEmail(),user.getRole().name());
        String refresh=jwtService.generateRefreshToken(user.getEmail(),user.getRole().name());

        return new AuthResponse(access,refresh);
    }

    // login
    public AuthResponse login(LoginRequest request){
        User user=userRepository.findByEmail(request.email())
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(request.password(),user.getPassword())){
            throw new RuntimeException("Invalid password");
        }

        String access=jwtService.generateAccessToken(user.getEmail(),user.getRole().name());
        String refresh=jwtService.generateRefreshToken(user.getEmail(),user.getRole().name());

        return new AuthResponse(access,refresh);
    }

    // refresh
    public AuthResponse refresh(String refreshToken){
        String email=jwtService.extractEmail(refreshToken);

        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));

        if (!jwtService.isValid(refreshToken,new UsersDetails(user))){
            throw new RuntimeException("Invalid refresh token");
        }

        String newAccess=jwtService.generateAccessToken(email,user.getRole().name());

        return new AuthResponse(newAccess,refreshToken);
    }


    // logout
    public void logout(String token){
        if (token==null || token.isBlank()){
            throw new RuntimeException("Token required");
        }
        blacklistService.blacklist(token);
    }


}
