package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.company.hrms.security.BlacklistedToken;

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken,Long> {

    boolean existsByToken(String token);

}
