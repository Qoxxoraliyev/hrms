package uz.company.hrms.dto;

import uz.company.hrms.enums.AwardType;

import java.time.LocalDate;

public record AwardCreateDTO(
        String employeeFullName,
        Integer awardCount,
        AwardType awardType,
        LocalDate awardDate
){}
