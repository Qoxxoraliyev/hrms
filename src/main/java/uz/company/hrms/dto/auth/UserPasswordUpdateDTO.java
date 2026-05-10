package uz.company.hrms.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserPasswordUpdateDTO(

        @NotBlank
        String oldPassword,

        @NotBlank
        @Size(min = 6,max = 100)
        String newPassword
) {}
