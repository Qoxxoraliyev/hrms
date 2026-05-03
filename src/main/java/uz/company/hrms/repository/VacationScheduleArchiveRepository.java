package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.VacationSchedule;
import uz.company.hrms.entity.VacationScheduleArchive;

@Repository
public interface VacationScheduleArchiveRepository extends JpaRepository<VacationScheduleArchive,Long> {


}
