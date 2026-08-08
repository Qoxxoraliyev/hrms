package uz.company.hrms.dto;


import uz.company.hrms.enums.VacationMonth;

public record VacationScheduleUpdateDTO(

        Long id,
        String employeeName,
        String departmentName,
        VacationMonth vacationMonth
) {}
