package uz.company.hrms.service;


import uz.company.hrms.dto.EmployeeCreateDTO;
import uz.company.hrms.dto.EmployeeDeleteDTO;
import uz.company.hrms.dto.EmployeeResponseDTO;
import uz.company.hrms.dto.YoungEmployeeDTO;
import uz.company.hrms.enums.Rank;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO employeeCreateDTO(EmployeeCreateDTO dto);

    List<EmployeeResponseDTO> getAll();

    void deleteEmployee(EmployeeDeleteDTO dto);

    List<EmployeeResponseDTO> filterByAge(Integer minAge,Integer maxAge);

    List<YoungEmployeeDTO> getYoungEmployee();

    List<EmployeeResponseDTO> filter(
            Rank rank,
            Long departmentId,
            Long positionId,
            Integer minExperience,
            Integer maxExperience
    );


}
