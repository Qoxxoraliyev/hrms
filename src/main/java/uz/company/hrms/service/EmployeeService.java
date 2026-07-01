package uz.company.hrms.service;


import uz.company.hrms.dto.*;
import uz.company.hrms.enums.Rank;
import uz.company.hrms.service.Impl.StaffPositionServiceImpl;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO employeeCreateDTO(EmployeeCreateDTO dto);

    List<EmployeeResponseDTO> getAll();

    void deleteEmployee(EmployeeDeleteDTO dto);

    List<EmployeeResponseDTO> filterByAge(Integer minAge,Integer maxAge);

    List<YoungEmployeeDTO> getYoungEmployee();

    List<ArchiveEmployeeDTO> getArchiveEmployee();

    List<StaffTableResponseDTO> getStaffTable();

    List<StaffTableResponseDTO> getStaffTableByDepartment(String departmentName);

    List<StaffTableResponseDTO> getStaffTableByPosition(String positionName);

    List<StaffTableResponseDTO> getStaffTableByName(String fullName);

    List<EmployeeResponseDTO> filter(
            Rank rank,
            String departmentName,
            String staffPositionName,
            Integer minExperience,
            Integer maxExperience
    );


}
