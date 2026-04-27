package uz.company.hrms.service;


import uz.company.hrms.dto.EmployeeCreateDTO;
import uz.company.hrms.dto.EmployeeResponseDTO;

public interface EmployeeService {

    EmployeeResponseDTO employeeCreateDTO(EmployeeCreateDTO dto);

}
