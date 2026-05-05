package uz.company.hrms.mapper;

import uz.company.hrms.dto.SickLeaveResponseDTO;
import uz.company.hrms.entity.SickLeave;

public class SickLeaveMapper {


    public static SickLeaveResponseDTO toDTO(SickLeave sickLeave){
        return new SickLeaveResponseDTO(
                sickLeave.getId(),
                sickLeave.getDiseaseName(),
                sickLeave.getDays(),
                sickLeave.getStartDate(),
                sickLeave.getEndDate(),
                sickLeave.getEmployee().getFullName()
        );
    }


}
