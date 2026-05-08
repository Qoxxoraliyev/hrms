package uz.company.hrms.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import uz.company.hrms.enums.Rank;

import java.time.LocalDate;

public record EmployeeCreateDTO(

        @NotBlank
        String fullName,

        @NotNull
        Rank rank,

        @NotNull
        String departmentName,

        @NotNull
        String staffPositionName,

        @NotNull
        LocalDate birthDate,

        @NotBlank
        String address,

        @NotNull
        LocalDate employmentDate,

        @NotNull
        LocalDate rankAssignedDate,

        @NotNull
        Integer awardCountFromOffice,

        @NotNull
        Integer appreciationCountFromTashkent
) {}
