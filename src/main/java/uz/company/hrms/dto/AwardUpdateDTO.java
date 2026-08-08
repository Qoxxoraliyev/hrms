package uz.company.hrms.dto;

import uz.company.hrms.enums.AwardType;

import java.time.LocalDate;

public record AwardUpdateDTO(

        Long id,
        String employeeFullName,
        Integer awardCount,
        AwardType awardType,
        LocalDate awardDate


) {}
