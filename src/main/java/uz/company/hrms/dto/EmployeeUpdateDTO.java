package uz.company.hrms.dto;

import uz.company.hrms.enums.Rank;

import java.time.LocalDate;


public record EmployeeUpdateDTO(

        Long id,
        String fullName,
        Rank rank,
        String departmentName,
        String staffPositionName,
        LocalDate birthDate,
        String address,
        LocalDate employmentDate,
        LocalDate rankAssignedDate,
        Integer awardCountFromOffice,
        Integer appreciationCountFromTashkent,
        String phoneNumber
) {}
