package uz.company.hrms.service;

import uz.company.hrms.dto.EmployeeNameDTO;
import uz.company.hrms.dto.SickLeaveArchiveDTO;
import uz.company.hrms.dto.SickLeaveCreateDTO;
import uz.company.hrms.dto.SickLeaveResponseDTO;

import java.util.List;

public interface SickLeaveService {

    SickLeaveResponseDTO createSickLeave(SickLeaveCreateDTO dto);

    List<SickLeaveResponseDTO> getAll();

    List<SickLeaveResponseDTO> filterByEmployeeFullName(String fullName);

    List<EmployeeNameDTO> getEmployees();

    List<SickLeaveArchiveDTO> getSickLeaveArchive();

    void delete(Long id);


}
