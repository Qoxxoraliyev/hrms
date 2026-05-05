package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.SickLeaveArchive;

@Repository
public interface SickLeaveArchiveRepository extends JpaRepository<SickLeaveArchive,Long> {

}
