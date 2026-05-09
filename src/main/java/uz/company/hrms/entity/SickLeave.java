package uz.company.hrms.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "sick_leaves",
        indexes = {
                @Index(name = "idx_sick_leave_employee",columnList = "employee_id"),
                @Index(name = "idx_sick_leave_start_date",columnList = "start_date"),
        }
)
public class SickLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "employee_id",
    nullable = false,
    foreignKey = @ForeignKey(name = "fk_sick_leave_archive_employee"))
    private Employee employee;

    private LocalDate startDate;

    private LocalDate endDate;

    private Integer days;

    private String diseaseName;

    public SickLeave(){}

    public SickLeave(Long id, Employee employee, LocalDate startDate, LocalDate endDate, Integer days, String diseaseName) {
        this.id = id;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.days = days;
        this.diseaseName = diseaseName;
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


}
