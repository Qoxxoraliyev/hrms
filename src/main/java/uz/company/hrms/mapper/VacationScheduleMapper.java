package uz.company.hrms.mapper;


import uz.company.hrms.dto.VacationScheduleResponseDTO;
import uz.company.hrms.entity.VacationSchedule;

public class VacationScheduleMapper {

    public static VacationScheduleResponseDTO toDto(VacationSchedule entity){
        return new VacationScheduleResponseDTO(
                entity.getId(),
                entity.getEmployee().getFullName(),
                entity.getDepartment().getName(),
                entity.getVacationMonth()
        );
    }


}
