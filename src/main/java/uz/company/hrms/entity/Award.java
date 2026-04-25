package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import uz.company.hrms.enums.AwardType;

import java.time.LocalDate;

@Entity
@Table(name = "awards",
indexes = {
        @Index(name = "idx_award_employee",columnList = "employee_id"),
        @Index(name = "idx_award_date",columnList = "award_date"),
        @Index(name = "idx_award_type",columnList = "award_type")
})
public class Award {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(
            name = "employee_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_award_employee")
    )
    private Employee employee;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "award_type",nullable = false)
    private AwardType awardType;

    @NotNull
    @Column(name = "award_date",nullable = false)
    private LocalDate awardDate;

    public Award(){}

    public Award(Long id, Employee employee, AwardType awardType, LocalDate awardDate) {
        this.id = id;
        this.employee = employee;
        this.awardType = awardType;
        this.awardDate = awardDate;
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

    public AwardType getAwardType() {
        return awardType;
    }

    public void setAwardType(AwardType awardType) {
        this.awardType = awardType;
    }

    public LocalDate getAwardDate() {
        return awardDate;
    }

    public void setAwardDate(LocalDate awardDate) {
        this.awardDate = awardDate;
    }

}
