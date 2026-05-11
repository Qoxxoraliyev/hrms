package uz.company.hrms.dto;

import java.time.LocalDate;

public record SickLeaveArchiveDTO(
        Long id,
        String employeeName,
        LocalDate startDate,
        LocalDate endDate,
        Integer days,
        String diseaseName,
        Integer year,
        LocalDate archivedAt
) {}
