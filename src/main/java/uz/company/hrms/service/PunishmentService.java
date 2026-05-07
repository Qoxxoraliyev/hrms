package uz.company.hrms.service;

import uz.company.hrms.dto.EmployeeNameDTO;
import uz.company.hrms.dto.PunishmentCreateDTO;
import uz.company.hrms.dto.PunishmentResponseDTO;

import java.util.List;

public interface PunishmentService {

    PunishmentResponseDTO createPunishment(PunishmentCreateDTO dto);

    List<PunishmentResponseDTO> getAll();

    List<PunishmentResponseDTO> filterByEmployeeFullName(String employeeFullName);

    List<EmployeeNameDTO> getEmployees();

    void delete(Long id);
}
