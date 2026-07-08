package uz.company.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.company.hrms.entity.Document;
import uz.company.hrms.enums.DocumentType;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Long> {


    List<Document> findByEmployeeId(Long employeeId);

    long countByEmployeeId(Long employeeId);

    boolean existsByEmployeeId(Long employeeId);

    boolean existsByEmployeeIdAndType(Long employeeId, DocumentType type);

    Optional<Document> findByEmployeeIdAndType(Long employeeId,DocumentType type);

    List<Document> findByEmployee_FirstNameContainingIgnoreCase(String firstName);



}
