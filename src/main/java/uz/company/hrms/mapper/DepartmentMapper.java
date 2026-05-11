package uz.company.hrms.mapper;

import uz.company.hrms.dto.DepartmentResponseDTO;
import uz.company.hrms.entity.Department;

public class DepartmentMapper {

    public static DepartmentResponseDTO toDTO(Department department){
        return new DepartmentResponseDTO(
                department.getId(),
                department.getName()
        );
    }


}
