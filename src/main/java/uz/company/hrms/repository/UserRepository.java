package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.User;
import uz.company.hrms.enums.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByEmail(String email);

    List<User> findByRole(Role role);

    Optional<User> findByEmail(String email);


}
