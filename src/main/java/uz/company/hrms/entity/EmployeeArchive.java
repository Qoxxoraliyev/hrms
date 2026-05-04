package uz.company.hrms.entity;

import jakarta.persistence.*;
import uz.company.hrms.enums.RetirementReason;
import java.time.LocalDate;

@Entity
@Table(name = "employee_archives")
public class EmployeeArchive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long oldEmployeeId;

    private String fullName;

    private LocalDate birthDate;

    private LocalDate employmentDate;

    private LocalDate leavingDate;

    private String departmentName;

    private String positionName;

    private String experience;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RetirementReason retirementReason;

    public EmployeeArchive(){}

    public EmployeeArchive(Long id, Long oldEmployeeId, String fullName, LocalDate birthDate, LocalDate employmentDate, LocalDate leavingDate, String departmentName, String positionName, String experience, RetirementReason retirementReason) {
        this.id = id;
        this.oldEmployeeId = oldEmployeeId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.employmentDate = employmentDate;
        this.leavingDate = leavingDate;
        this.departmentName = departmentName;
        this.positionName = positionName;
        this.experience = experience;
        this.retirementReason = retirementReason;
    }

    public Long getId() {
        return id;
    }

    public Long getOldEmployeeId() {
        return oldEmployeeId;
    }

    public void setOldEmployeeId(Long oldEmployeeId) {
        this.oldEmployeeId = oldEmployeeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getLeavingDate() {
        return leavingDate;
    }

    public void setLeavingDate(LocalDate leavingDate) {
        this.leavingDate = leavingDate;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public RetirementReason getRetirementReason() {
        return retirementReason;
    }

    public void setRetirementReason(RetirementReason retirementReason) {
        this.retirementReason = retirementReason;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
