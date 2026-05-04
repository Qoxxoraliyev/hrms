package uz.company.hrms.dto;

import uz.company.hrms.enums.RetirementReason;

public record EmployeeDeleteDTO(

        String employeeFullName,
        RetirementReason retirementReason,
        Boolean archive

) {}
