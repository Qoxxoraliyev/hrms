package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.Position;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {

    Optional<Position> findByName(String name);
}
