package uz.company.hrms.dto;

import uz.company.hrms.enums.AwardType;

import java.time.LocalDate;

public record AwardResponseDTO(
        Long id,
        String employeeName,
        Integer awardCount,
        AwardType awardType,
        LocalDate awardDate
) {}
