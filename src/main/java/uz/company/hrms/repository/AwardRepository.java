package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.Award;

import java.util.List;

@Repository
public interface AwardRepository extends JpaRepository<Award,Long> {

    List<Award> findByEmployee_FullName(String employeeFullName);

}
