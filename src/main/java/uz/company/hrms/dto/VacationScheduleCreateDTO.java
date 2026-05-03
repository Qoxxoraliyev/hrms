package uz.company.hrms.dto;

import uz.company.hrms.enums.VacationMonth;
public record VacationScheduleCreateDTO(

        String employeeName,
        String departmentName,
        VacationMonth vacationMonth
) {}
