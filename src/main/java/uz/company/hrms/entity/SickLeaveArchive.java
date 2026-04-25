package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sick_leave_archives",
indexes = {
        @Index(name = "idx_sick_archive_year",columnList = "year"),
        @Index(name = "idx_sick_archive_employee",columnList = "employee_id")
})
public class SickLeaveArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "original_id",nullable = false)
    private Long originalId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id",nullable = false)
    private Employee employee;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    @NotNull
    private Integer days;

    @NotNull
    private String diseaseName;

    @NotNull
    private Integer year;

    @NotNull
    private LocalDateTime archivedAt;

    public SickLeaveArchive(){}

    public SickLeaveArchive(Long id, Long originalId, Employee employee, LocalDate startDate, LocalDate endDate, Integer days, String diseaseName, Integer year, LocalDateTime archivedAt) {
        this.id = id;
        this.originalId = originalId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.diseaseName = diseaseName;
        this.year = year;
        this.archivedAt = archivedAt;
    }

    public Long getId() {
        return id;
    }

    public Long getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Long originalId) {
        this.originalId = originalId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

}
