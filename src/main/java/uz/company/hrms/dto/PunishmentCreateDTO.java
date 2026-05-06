package uz.company.hrms.dto;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import uz.company.hrms.enums.PunishmentType;

import java.time.LocalDate;

public record PunishmentCreateDTO(
        String employeeFullName,
        Integer punishmentCount,
        PunishmentType punishmentType,
        LocalDate punishmentDate
) {}
