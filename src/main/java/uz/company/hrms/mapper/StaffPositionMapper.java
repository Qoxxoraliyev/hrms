package uz.company.hrms.mapper;


import uz.company.hrms.dto.StaffPositionResponseDTO;
import uz.company.hrms.entity.StaffPosition;

public class StaffPositionMapper {

    public static StaffPositionResponseDTO toDTO(StaffPosition staffPosition){
        return new StaffPositionResponseDTO(
                staffPosition.getId(),
                staffPosition.getPositionName()
        );
    }


}
