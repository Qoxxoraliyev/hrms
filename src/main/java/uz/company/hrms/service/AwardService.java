package uz.company.hrms.service;

import uz.company.hrms.dto.AwardCreateDTO;
import uz.company.hrms.dto.AwardResponseDTO;

import java.util.List;

public interface AwardService {

    AwardResponseDTO createAward(AwardCreateDTO dto);

    List<AwardResponseDTO> getAll();

    List<AwardResponseDTO> filterByEmployeeName(String EmployeeFullName);

    void delete(Long id);

}
