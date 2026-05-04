package uz.company.hrms.service;

import uz.company.hrms.dto.PensionerCreateDTO;
import uz.company.hrms.dto.PensionerResponseDTO;

import java.util.List;

public interface PensionerService {

    PensionerResponseDTO createPensioner(PensionerCreateDTO dto);

    List<PensionerResponseDTO> getAll();

    List<PensionerResponseDTO> filterByDepartmentName(String departmentName);

    void delete(Long id);


}
