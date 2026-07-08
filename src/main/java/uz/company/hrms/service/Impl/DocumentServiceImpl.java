package uz.company.hrms.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.company.hrms.dto.DocumentResponse;
import uz.company.hrms.entity.Document;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.enums.DocumentType;
import uz.company.hrms.exeption.FileStorageException;
import uz.company.hrms.exeption.ResourceNotFoundException;
import uz.company.hrms.mapper.DocumentMapper;
import uz.company.hrms.repository.DocumentRepository;
import uz.company.hrms.repository.EmployeeRepository;
import uz.company.hrms.service.DocumentService;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {


    @Value("${file.upload-dir}")
    private String uploadDir;


    private final DocumentRepository documentRepository;
    private final EmployeeRepository employeeRepository;


    public DocumentServiceImpl(DocumentRepository documentRepository, EmployeeRepository employeeRepository) {
        this.documentRepository = documentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    @Override
    public void upload(Long employeeId, DocumentType type, MultipartFile file) {

        // Employee mavjudligini tekshirish
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + employeeId));

        // Bir xil turdagi hujjat mavjudligini tekshirish
        if (documentRepository.existsByEmployeeIdAndType(employeeId, type)) {
            throw new IllegalStateException("This document already exists.");
        }

        // File bo'sh emasligini tekshirish
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty.");
        }

        Path uploadPath = Paths.get(uploadDir);

        String storedFileName;
        Path filePath;

        try {

            // uploads papkasini yaratish
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Serverda saqlanadigan nom
            storedFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

            filePath = uploadPath.resolve(storedFileName);

            Files.copy(file.getInputStream(), filePath);

        } catch (IOException e) {
            throw new FileStorageException("Failed to upload file.", e);
        }

        Document document = new Document();

        document.setEmployee(employee);
        document.setType(type);
        document.setFileName(file.getOriginalFilename());
        document.setFilePath(filePath.toString());
        document.setFileSize(file.getSize());
        document.setContentType(file.getContentType());

        // Bazaga saqlash
        documentRepository.save(document);
    }

    @Override
    public List<DocumentResponse> getEmployeeDocuments(Long employeeId){

        if (!employeeRepository.existsById(employeeId)){
            throw new ResourceNotFoundException(
                    "Employee not found with id: "+employeeId);
        }

        List<Document> documents=documentRepository.findByEmployeeId(employeeId);
        return DocumentMapper.toDTOList(documents);
    }

    @Override
    public long countDocuments(Long employeeId){
        if (!employeeRepository.existsById(employeeId)){
            throw new ResourceNotFoundException("Employee not found with id: "+employeeId);
        }
        return documentRepository.countByEmployeeId(employeeId);
    }



    @Override
    public Resource download(Long downloadId){
        Document document=documentRepository.findById(downloadId)
                .orElseThrow(()->new ResourceNotFoundException("Document not found"));

        try {
            Path path=Paths.get(document.getFilePath());
            Resource resource=new UrlResource(path.toUri());
            if (resource.exists() && resource.isReadable()){
                return resource;
            }

            throw new ResourceNotFoundException("File not found.");

        }
        catch (IOException e){
            throw new FileStorageException("Could not read file.",e);
        }
    }

    @Transactional
    @Override
    public void replace(Long documentId, MultipartFile file) {

        Document document=documentRepository.findById(documentId)
                .orElseThrow(()->new ResourceNotFoundException("Document not found with id: "+documentId));

        if (file.isEmpty()){
            throw new IllegalArgumentException("File is empty.");
        }

        try {
            Files.deleteIfExists(Paths.get(document.getFilePath()));

            Path uploadPath=Paths.get(uploadDir);

            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }

            String storageFileName=UUID.randomUUID()+"_"+file.getOriginalFilename();

            Path filePath=uploadPath.resolve(storageFileName);
            Files.copy(file.getInputStream(),filePath);

            document.setFileName(file.getOriginalFilename());
            document.setFilePath(filePath.toString());
            document.setFileSize(file.getSize());
            document.setContentType(file.getContentType());

            documentRepository.save(document);
        }
        catch (IOException e){
            throw new FileStorageException("Failed to replace file.",e);
        }


    }


    @Transactional
    @Override
    public void delete(Long documentId){
        Document document=documentRepository.findById(documentId)
                .orElseThrow(()->
                        new ResourceNotFoundException("Document not found"));
        try {
            Files.deleteIfExists(Paths.get(document.getFilePath()));
        }
        catch (IOException e){
            throw new FileStorageException("Failed to delete file.",e);
        }
        documentRepository.delete(document);
    }

    @Override
    public DocumentResponse getEmployeeDocumentByType(Long employeeId, DocumentType type) {

        Document document = documentRepository
                .findByEmployeeIdAndType(employeeId, type)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Document not found."));

        return DocumentMapper.toDTO(document);
    }



    @Override
    public List<DocumentResponse> searchByEmployeeFirstName(String firstName){
        List<Document> documents=documentRepository.findByEmployee_FirstNameContainingIgnoreCase(firstName);

        if (documents.isEmpty()){
            throw new ResourceNotFoundException(
                    "No documents found for employee name: "+firstName);
        }

        return DocumentMapper.toDTOList(documents);
    }





}
