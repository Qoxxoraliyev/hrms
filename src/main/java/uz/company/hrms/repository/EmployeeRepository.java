package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.Employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, JpaSpecificationExecutor<Employee> {

    List<Employee> findByBirthDateAfter(LocalDate birthDate);

    List<Employee> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);

    Optional<Employee> findByFullName(String fullName);

    List<Employee> findByEmploymentDateAfter(LocalDate date);


}
