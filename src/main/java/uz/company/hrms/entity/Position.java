package uz.company.hrms.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "positions",
uniqueConstraints = {
        @UniqueConstraint(name = "uk_position_name",columnNames = "name")
})
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Position name is required")
    @Size(min = 2,max = 120,message = "Position name must be between 2 and 120 characters")
    @Column(name = "name",nullable = false,unique = true,length = 120)
    private String name;

    @OneToMany(mappedBy = "position",fetch = FetchType.LAZY)
    private List<Employee> employees=new ArrayList<>();

    public Position(){}

    public Position(Long id, String name) {
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
