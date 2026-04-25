package uz.company.hrms.dto;

import uz.company.hrms.enums.Role;

public record UserResponseDTO(
        Long id,
        String fullName,
        Role role
) {}
