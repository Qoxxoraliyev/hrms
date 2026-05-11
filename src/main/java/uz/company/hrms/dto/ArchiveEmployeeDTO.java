package uz.company.hrms.dto;

import uz.company.hrms.enums.RetirementReason;

import java.time.LocalDate;

public record ArchiveEmployeeDTO(

        Long id,
        String fullName,
        LocalDate birthDate,
        LocalDate employmentDate,
        LocalDate leavingDate,
        String departmentName,
        String positionName,
        RetirementReason retirementReason
) {}
