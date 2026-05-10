package uz.company.hrms.security.service;

import org.springframework.stereotype.Service;
import uz.company.hrms.repository.BlacklistedTokenRepository;

@Service
public class TokenBlacklistService {

    private final BlacklistedTokenRepository repository;

    public TokenBlacklistService(BlacklistedTokenRepository repository) {
        this.repository = repository;
    }

    public void blacklist(String token){
        if (!repository.existsByToken(token)){
            BlacklistedToken blacklistedToken=new BlacklistedToken();
            blacklistedToken.setToken(token);
            repository.save(blacklistedToken);
        }
    }

    public  boolean isBlacklisted(String token){
       return repository.existsByToken(token);
    }

}
