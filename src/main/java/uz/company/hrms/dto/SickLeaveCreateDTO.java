package uz.company.hrms.dto;

import java.time.LocalDate;

public record SickLeaveCreateDTO(

        String employeeFullName,
        LocalDate startDate,
        LocalDate endDate,
        Integer days,
        String diseaseName
){}
