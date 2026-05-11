package uz.company.hrms.service;

import uz.company.hrms.dto.DepartmentCreateDTO;
import uz.company.hrms.dto.DepartmentResponseDTO;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO departmentCreate(DepartmentCreateDTO dto);

    List<DepartmentResponseDTO> getAll();

    void delete(Long id);

}
