package uz.company.hrms.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.company.hrms.dto.DocumentResponse;
import uz.company.hrms.enums.DocumentType;
import uz.company.hrms.service.DocumentService;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(
            @RequestParam Long employeeId,
            @RequestParam DocumentType type,
            @RequestParam MultipartFile file){

        documentService.upload(employeeId,type,file);

        return ResponseEntity.ok("Document uploaded successfully.");
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<DocumentResponse>> getEmployeeDocuments(@PathVariable Long employeeId){

        return ResponseEntity.ok(documentService.getEmployeeDocuments(employeeId));
    }

    @GetMapping("/count/{employeeId}")
    public ResponseEntity<Long> countDocuments(@PathVariable Long employeeId){
        return ResponseEntity.ok(documentService.countDocuments(employeeId));
    }

    @GetMapping("/download/{documentId}")
    public ResponseEntity<Resource> download(@PathVariable Long documentId){
        Resource resource = documentService.download(documentId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @PutMapping(value = "/{documentId}",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> replace(@PathVariable Long documentId,
                                          @RequestParam MultipartFile file){
        documentService.replace(documentId,file);

        return ResponseEntity.ok("Document deleted successfully.");
    }


    @GetMapping("/filter")
    public ResponseEntity<DocumentResponse> getEmployeeDocumentByType(
            @RequestParam Long employeeId,
            @RequestParam DocumentType type){
        return ResponseEntity.ok(documentService.getEmployeeDocumentByType(employeeId,type));
    }


    @GetMapping("/search")
    public ResponseEntity<List<DocumentResponse>> searchByEmployeeFullName(@RequestParam String firstName){
        return ResponseEntity.ok(documentService.searchByEmployeeFullName(firstName));
    }



}
