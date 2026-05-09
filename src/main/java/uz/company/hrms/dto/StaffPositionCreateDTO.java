package uz.company.hrms.dto;

public record StaffPositionCreateDTO(
        String positionName,
        String departmentName,
        Integer totalSlots,
        Integer occupiedSlots
){}
