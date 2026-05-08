package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "staff_positions",
uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_department_position",
                columnNames = {"department_id","position_name"}
        )
})
public class StaffPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lavozimi nomi
    @NotBlank
    @Column(name = "position_name",nullable = false,length = 100)
    private String positionName;

    @NotNull(message = "Department is required")
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "department_id",nullable = false)
    private Department department;

    // jami shtat
    @NotNull
    @Column(name = "total_slots",nullable = false)
    private Integer totalSlots;

    // band shtat
    @NotNull
    @Column(name = "occupied_slots",nullable = false)
    private Integer occupiedSlots=0;

    @OneToMany(mappedBy = "staffPosition",fetch =FetchType.LAZY)
    private List<Employee> employees=new ArrayList<>();

    public StaffPosition(){}

    public StaffPosition(Long id, String positionName, Department department, Integer totalSlots, Integer occupiedSlots, List<Employee> employees) {
        this.id = id;
        this.positionName = positionName;
        this.department = department;
        this.totalSlots = totalSlots;
        this.occupiedSlots = occupiedSlots;
        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Integer getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(Integer totalSlots) {
        this.totalSlots = totalSlots;
    }

    public Integer getOccupiedSlots() {
        return occupiedSlots;
    }

    public void setOccupiedSlots(Integer occupiedSlots) {
        this.occupiedSlots = occupiedSlots;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

}
