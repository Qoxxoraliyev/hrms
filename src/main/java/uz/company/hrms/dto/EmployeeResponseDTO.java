package uz.company.hrms.dto;

import uz.company.hrms.entity.Department;
import uz.company.hrms.entity.Position;
import uz.company.hrms.enums.NextAttestation;
import uz.company.hrms.enums.Rank;

import java.time.LocalDate;

public record EmployeeResponseDTO(
        Long id,
        String fullName,
        Rank rank,
        Department department,
        Position position,
        LocalDate birthDate,
        String address,
        LocalDate employmentDate,
        LocalDate rankAssignedDate,
        Integer awardCountFromOffice,
        Integer appreciationCountFromTashkent,
        LocalDate nextAttestationDate,
        NextAttestation nextAttestation
) {}
