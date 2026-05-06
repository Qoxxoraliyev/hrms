package uz.company.hrms.dto;

import uz.company.hrms.enums.PunishmentType;

import java.time.LocalDate;

public record PunishmentResponseDTO (
        Long id,
        String employeeFullName,
        Integer punishmentCount,
        PunishmentType punishmentType,
        LocalDate punishmentDate
){}
