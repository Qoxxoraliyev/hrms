package uz.company.hrms.dto;

import uz.company.hrms.enums.RetirementReason;

import java.time.LocalDate;

public record PensionerResponseDTO(

        Long id,
        String fullName,
        LocalDate birthDate,
        LocalDate retirementDate,
        String departmentName,
        String phoneNumber,
        RetirementReason retirementReason
) {}
