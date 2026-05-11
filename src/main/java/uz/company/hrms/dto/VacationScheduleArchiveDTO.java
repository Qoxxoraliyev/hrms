package uz.company.hrms.dto;

import uz.company.hrms.enums.VacationMonth;

import java.time.LocalDate;

public record VacationScheduleArchiveDTO(
        Long id,
        String employeeName,
        String departmentName,
        VacationMonth vacationMonth,
        LocalDate archivedAt
) {}
