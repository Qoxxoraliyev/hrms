package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import uz.company.hrms.enums.DocumentType;

@Entity
@Table(name = "documents",indexes = {
        @Index(name = "idx_document_employee",columnList = "employee_id"),
        @Index(name = "idx_document_type",columnList = "type")
})
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DocumentType type;

    @Size(max = 255)
    @Column(name = "file_name")
    private String fileName;

    @NotNull
    @Column(name = "file_path",nullable = false)
    private String filePath;

    private Long fileSize;

    private String contentType;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "employee_id",
    nullable = false,
    foreignKey = @ForeignKey(name = "fk_document_employee"))
    private Employee employee;

    public Document(){}

    public Document(Long id, DocumentType type, String fileName, String filePath, Long fileSize, String contentType) {
        this.id = id;
        this.type = type;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.contentType = contentType;
    }

    public Long getId() {
        return id;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
