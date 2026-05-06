package uz.company.hrms.mapper;

import uz.company.hrms.dto.PunishmentCreateDTO;
import uz.company.hrms.dto.PunishmentResponseDTO;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.entity.Punishment;

public class PunishmentMapper {

    public static PunishmentResponseDTO toDTO(Punishment punishment){
        return new PunishmentResponseDTO(
                punishment.getId(),
                punishment.getEmployee().getFullName(),
                punishment.getPunishmentCount(),
                punishment.getPunishmentType(),
                punishment.getPunishmentDate()
        );
    }


}
