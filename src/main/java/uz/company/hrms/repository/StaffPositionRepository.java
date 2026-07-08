package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import uz.company.hrms.entity.StaffPosition;

import java.util.List;
import java.util.Optional;

@Repository
public interface StaffPositionRepository extends JpaRepository<StaffPosition,Long> {

    Optional<StaffPosition> findByPositionNameAndDepartment_Name(
            String positionName,
            String departmentName
    );

    @Query("""
            select s
            from StaffPosition s
            where s.occupiedSlots<s.totalSlots
            """)
    List<StaffPosition> findVacantPositions();


    @Query("""
            select s
            from StaffPosition s
            where s.department.name=:departmentName
            and s.occupiedSlots=s.totalSlots
            """)
    List<StaffPosition> findVacantPositionsByDepartment( @RequestParam("departmentName") String departmentName);


    @Query("""
            select s 
            from StaffPosition s
            where s.positionName=:positionName
            and s.occupiedSlots<s.totalSlots
            """)
    List<StaffPosition> findVacantPositionByPosition(
            @Param("positionName") String positionName
    );


    @Query("""
            select s
            from StaffPosition s
            where s.occupiedSlots=s.totalSlots
            """)
    List<StaffPosition> findFullPositions();


    @Query("""
            select s
            from StaffPosition s
            where s.department.name=:deartmentName
            and s.occupiedSlots=s.totalSlots
            """)
    List<StaffPosition> findFullPositionsByDepartment(
            @Param("departmentName") String departmentName
    );

    @Query("""
    select s
    from StaffPosition s
    where s.positionName = :positionName
      and s.occupiedSlots = s.totalSlots
""")
    List<StaffPosition> findFullPositionsByPosition(
            @Param("positionName") String positionName
    );



}
