package uz.company.hrms.dto;

import java.time.LocalDate;

public record SickLeaveResponseDTO(
        Long id,
        String employeeFullName,
        Integer days,
        LocalDate startDate,
        LocalDate endDate,
        String diseaseName
) {}
