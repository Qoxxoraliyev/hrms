package uz.company.hrms.dto;
import uz.company.hrms.enums.DocumentType;

public record DocumentResponse(

        Long id,
        DocumentType type,
        String fileName,
        Long fileSize,
        String contentType,
        String fileUrl

) {
}
