package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import uz.company.hrms.enums.RetirementReason;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "pensioners",
        indexes = {@Index(name = "idx_pensioner_department",columnList = "department_id"),
                @Index(name = "idx_pensioner_retirement_date",columnList = "retirement_date"),
                @Index(name = "idx_pensioner_reason",columnList = "retirement_reason"),
                @Index(name = "idx_pensioner_full_name",columnList = "full_name")
        }
)
public class Pensioner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Full name is required")
    @Size(min = 3,max = 150,message = "Full name must be between 3 and 150 characters")
    private String fullName;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must  be in the past")
    @Column(name = "birth_date",nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "Retirement is required")
    @Column(name = "retirement_date",nullable = false)
    private LocalDate retirementDate;

    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(
            name = "department_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_pensioner_department")
    )
    private Department department;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^[+]?[0-9]{9,15}$",
            message = "Phone number must be valid"
    )
    @Column(name = "phone_number",nullable = false,length = 20)
    private String phoneNumber;

    @NotNull(message = "Retirement reason is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "retirement_reason",nullable = false,length = 50)
    private RetirementReason retirementReason;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "employee_id",nullable = false,foreignKey = @ForeignKey(name = "fk_pensioner_employee"))
    private Employee employee;

    public Pensioner(){}

    public Pensioner(Long id, String fullName, LocalDate birthDate, LocalDate retirementDate, Department department, String phoneNumber, RetirementReason retirementReason) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.retirementDate = retirementDate;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.retirementReason = retirementReason;
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

    public LocalDate getRetirementDate() {
        return retirementDate;
    }

    public void setRetirementDate(LocalDate retirementDate) {
        this.retirementDate = retirementDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public RetirementReason getRetirementReason() {
        return retirementReason;
    }

    public void setRetirementReason(RetirementReason retirementReason) {
        this.retirementReason = retirementReason;
    }
}
