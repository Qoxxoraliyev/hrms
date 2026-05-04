package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.Pensioner;

import java.util.List;

@Repository
public interface PensionerRepository extends JpaRepository<Pensioner,Long> {

    List<Pensioner> findByDepartment_Name(String departmentName);


}
