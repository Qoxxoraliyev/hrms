package uz.company.hrms.mapper;

import uz.company.hrms.dto.StaffTableResponseDTO;
import uz.company.hrms.entity.Employee;

public class StaffTableMapper {

    public static StaffTableResponseDTO toDTO(Employee employee){
        return new StaffTableResponseDTO(
                employee.getStaffPosition().getPositionName(),
                employee.getFullName()
        );
    }


}
