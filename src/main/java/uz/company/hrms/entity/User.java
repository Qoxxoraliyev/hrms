
package uz.company.hrms.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import uz.company.hrms.enums.Role;

@Entity
@Table(name = "users",indexes = {
        @Index(name = "idx_user_role",columnList = "role")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @NotBlank
    @Size(min = 3,max = 100)
    @Column(nullable = false)
    private String fullName;

    @NotBlank
    @Size(min = 6,max = 100,message = "Password must be at least 6 characters")
    @Column(nullable = false)
    private String password;

    @NotNull(message = "Role is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private Employee employee;

    public User(){}

    public User(Long id, String email, String fullName, String password, Role role) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
