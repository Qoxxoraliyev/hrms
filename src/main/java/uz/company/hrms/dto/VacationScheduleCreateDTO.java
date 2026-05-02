package uz.company.hrms.dto;

import uz.company.hrms.entity.Department;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.enums.VacationMonth;

import java.time.LocalDate;

public record VacationScheduleCreateDTO(

        Long originalScheduledId,
        Employee employee,
        Department department,
        VacationMonth vacationMonth,
        LocalDate archivedAt
) {}
