package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.dto.SickLeaveResponseDTO;
import uz.company.hrms.entity.SickLeave;

import java.util.List;

@Repository
public interface SickLeaveRepository extends JpaRepository<SickLeave,Long> {

    List<SickLeave> findByEmployee_FullName(String fullName);

}
