package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.Punishment;

import java.util.List;

@Repository
public interface PunishmentRepository extends JpaRepository<Punishment,Long> {

    List<Punishment> findByEmployee_FullName(String employeeFullName);

}
