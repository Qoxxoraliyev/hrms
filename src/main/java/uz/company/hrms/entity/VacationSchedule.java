package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import uz.company.hrms.enums.VacationMonth;

import java.time.LocalDateTime;

@Entity
public class VacationSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Original schedule id is required")
    @Column(name = "original_schedule_id",nullable = false)
    private Long originalScheduledId;

    @NotNull(message = "Employee is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "employee_id",nullable = false,
    foreignKey = @ForeignKey(name = "fk_vacation_schedule_employee"))
    private Employee employee;

    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "department_id",nullable = false,
    foreignKey = @ForeignKey(name = "fk_vacation_schedule_department"))
    private Department department;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "vacation_month",nullable = false)
    private VacationMonth vacationMonth;

    @NotNull(message = "Archived time is required")
    @Column(name = "archived_at",nullable = false)
    private LocalDateTime archivedAt;

    public VacationSchedule(){}

    public VacationSchedule(Long id, Employee employee, Department department, VacationMonth vacationMonth) {
        this.id = id;
        this.employee = employee;
        this.department = department;
        this.vacationMonth = vacationMonth;
    }

    @PrePersist
    public void prePersist(){
        if (this.archivedAt==null){
            this.archivedAt=LocalDateTime.now();
        }
    }

    public Long getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public VacationMonth getVacationMonth() {
        return vacationMonth;
    }

    public void setVacationMonth(VacationMonth vacationMonth) {
        this.vacationMonth = vacationMonth;
    }

    public Long getOriginalScheduledId() {
        return originalScheduledId;
    }

    public void setOriginalScheduledId(Long originalScheduledId) {
        this.originalScheduledId = originalScheduledId;
    }

}
