package uz.company.hrms.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_department_name",columnNames = "name")
})
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Size(min = 2,max = 100,message = "Department name must be between 2 and characters")
    @Column(name = "name",nullable = false,unique = true,length = 100)
    private String name;

    public Department(){}

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private List<Employee> employees=new ArrayList<>();

    public Department(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
