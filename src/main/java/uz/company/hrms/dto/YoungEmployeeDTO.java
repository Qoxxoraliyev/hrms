package uz.company.hrms.dto;

import java.time.LocalDate;

public record YoungEmployeeDTO(
        Long id,
        String employeeFullName,
        LocalDate employmentDate
) {}
