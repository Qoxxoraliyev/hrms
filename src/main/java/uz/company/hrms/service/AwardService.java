package uz.company.hrms.service;

import uz.company.hrms.dto.AwardCreateDTO;
import uz.company.hrms.dto.AwardResponseDTO;
import uz.company.hrms.dto.EmployeeNameDTO;

import java.util.List;

public interface AwardService {

    AwardResponseDTO createAward(AwardCreateDTO dto);

    List<AwardResponseDTO> getAll();

    List<AwardResponseDTO> filterByEmployeeName(String EmployeeFullName);

    List<EmployeeNameDTO> getEmployees();

    void delete(Long id);

}
