package uz.company.hrms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import uz.company.hrms.enums.Role;
public record UserCreateDTO(

        @NotBlank(message = "Full name is required")
        @Size(min = 3,max = 100)
        String fullName,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Size(min = 6,max = 100)
        String password,

        @NotNull
        Role role
) {}
