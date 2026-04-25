package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import uz.company.hrms.enums.RetirementReason;

import java.time.LocalDate;

@Entity
@Table(name = "dismissed_employees",
indexes = {
        @Index(name = "idx_dismissed_department",columnList = "department_id"),
        @Index(name = "idx_dismissed_date",columnList = "full_name")
})
public class DismissedEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Size(min = 3,max = 150)
    @Column(name = "full_name",nullable = false,length = 150)
    private String fullName;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "Dismissed date is required")
    @Column(name = "dismissed_date",nullable = false)
    private LocalDate dismissedDate;

    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "department_id",
               nullable = false,
            foreignKey = @ForeignKey(name = "fk_dismissed_employee_department")
    )
    private Department department;

    @NotBlank(message = "Dismissed reason is required")
    @Size(max = 500)
    @Column(name = "dismissed_reason",nullable = false,length = 500)
    private RetirementReason dismissedReason;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "employee_id",nullable = false,foreignKey = @ForeignKey(name = "fk_dismissed_employee_employee"))
    private Employee employee;

    public DismissedEmployee(){}

    public DismissedEmployee(Long id, String fullName, LocalDate birthDate, LocalDate dismissedDate, Department department, RetirementReason dismissedReason) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.dismissedDate = dismissedDate;
        this.department = department;
        this.dismissedReason = dismissedReason;
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDate getDismissedDate() {
        return dismissedDate;
    }

    public void setDismissedDate(LocalDate dismissedDate) {
        this.dismissedDate = dismissedDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public RetirementReason getDismissedReason() {
        return dismissedReason;
    }

    public void setDismissedReason(RetirementReason dismissedReason) {
        this.dismissedReason = dismissedReason;
    }


}
