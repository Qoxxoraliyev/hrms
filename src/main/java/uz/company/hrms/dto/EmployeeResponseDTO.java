package uz.company.hrms.dto;

import uz.company.hrms.enums.NextAttestation;
import uz.company.hrms.enums.Rank;

import java.time.LocalDate;

public record EmployeeResponseDTO(
        Long id,
        String fullName,
        Rank rank,
        String departmentName,
        String positionName,
        LocalDate birthDate,
        String address,
        LocalDate employmentDate,
        LocalDate rankAssignedDate,
        Integer awardCountFromOffice,
        Integer appreciationCountFromTashkent,
        LocalDate nextAttestationDate,
        NextAttestation nextAttestation
) {}
