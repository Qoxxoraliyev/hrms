package uz.company.hrms.service;

import org.springframework.web.multipart.MultipartFile;
import uz.company.hrms.dto.DocumentResponse;
import uz.company.hrms.enums.DocumentType;
import org.springframework.core.io.Resource;

import java.util.List;

public interface DocumentService {

    void upload(Long employeeId, DocumentType type, MultipartFile file);

    List<DocumentResponse> getEmployeeDocuments(Long employeeId);

    long countDocuments(Long employeeId);

    void delete(Long documentId);

    Resource download(Long documentId);

    void replace(Long documentId,MultipartFile file);

    DocumentResponse getEmployeeDocumentByType(Long employeeId,DocumentType type);

    List<DocumentResponse> searchByEmployeeFirstName(String firstName);

}
