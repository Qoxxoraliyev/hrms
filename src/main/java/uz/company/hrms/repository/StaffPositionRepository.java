package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.StaffPosition;

import java.util.Optional;

@Repository
public interface StaffPositionRepository extends JpaRepository<StaffPosition,Long> {

    Optional<StaffPosition> findByPositionNameAndDepartment_Name(
            String positionName,
            String departmentName
    );

}
