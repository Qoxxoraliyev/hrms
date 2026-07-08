package uz.company.hrms.service;

import org.springframework.data.repository.query.Param;
import uz.company.hrms.dto.StaffPositionCreateDTO;
import uz.company.hrms.dto.StaffPositionResponseDTO;
import uz.company.hrms.dto.VacancyResponseDTO;


import java.util.List;

public interface StaffPositionService {

    StaffPositionResponseDTO createStaffPosition(StaffPositionCreateDTO dto);

    StaffPositionResponseDTO updateStaffPosition(Long id,StaffPositionCreateDTO dto);

    StaffPositionResponseDTO getById(Long id);

    List<StaffPositionResponseDTO> getAll();

    void deleteStaffPosition(Long id);

    List<VacancyResponseDTO> getVacantPositions();

    List<VacancyResponseDTO> getVacantPositionsByDepartment(String departmentName);

    List<VacancyResponseDTO> getVacantPositionsByPosition(String positionName);

    List<VacancyResponseDTO> getFullPositions();

    List<VacancyResponseDTO> getFullPositionsByDepartment(String departmentName);

    List<VacancyResponseDTO> getFullPositionsByPosition(String positionName);



}
