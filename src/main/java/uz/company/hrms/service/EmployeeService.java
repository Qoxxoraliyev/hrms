package uz.company.hrms.service;


import uz.company.hrms.dto.*;
import uz.company.hrms.enums.Rank;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO employeeCreateDTO(EmployeeCreateDTO dto);

    List<EmployeeResponseDTO> getAll();

    void deleteEmployee(EmployeeDeleteDTO dto);

    List<EmployeeResponseDTO> filterByAge(Integer minAge,Integer maxAge);

    List<YoungEmployeeDTO> getYoungEmployee();

    List<ArchiveEmployeeDTO> getArchiveEmployee();

    List<EmployeeResponseDTO> filter(
            Rank rank,
            String departmentName,
            String staffPositionName,
            Integer minExperience,
            Integer maxExperience
    );


}
