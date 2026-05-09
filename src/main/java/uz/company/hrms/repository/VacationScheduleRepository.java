package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.VacationSchedule;
import uz.company.hrms.enums.VacationMonth;

import java.util.List;

@Repository
public interface VacationScheduleRepository extends JpaRepository<VacationSchedule,Long> {

    List<VacationSchedule> findByDepartment_Name(String departmentName);

    List<VacationSchedule> findByVacationMonth(VacationMonth vacationMonth);

}
