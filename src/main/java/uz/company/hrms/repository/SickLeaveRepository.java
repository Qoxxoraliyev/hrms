package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.SickLeave;

@Repository
public interface SickLeaveRepository extends JpaRepository<SickLeave,Long> {


}
