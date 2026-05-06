package uz.company.hrms.mapper;

import uz.company.hrms.dto.AwardResponseDTO;
import uz.company.hrms.entity.Award;

public class AwardMapper {

    public static AwardResponseDTO toDTO(Award award){
        return new AwardResponseDTO(
                award.getId(),
                award.getEmployee().getFullName(),
                award.getAwardCount(),
                award.getAwardType(),
                award.getAwardDate()
        );
    }


}
