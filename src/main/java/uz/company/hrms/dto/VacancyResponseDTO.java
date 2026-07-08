package uz.company.hrms.dto;

public record VacancyResponseDTO(
        String departmentName,
        String positionName,
        Integer totalSlots,
        Integer occupiedSlots,
        Integer vacantSlots
) {}
