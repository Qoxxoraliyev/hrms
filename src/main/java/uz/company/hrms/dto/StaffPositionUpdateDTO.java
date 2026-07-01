package uz.company.hrms.dto;

public record StaffPositionUpdateDTO(
        String positionName,
        String departmentName,
        Integer totalSlots
){}

