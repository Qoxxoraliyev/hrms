package uz.company.hrms.service;

import uz.company.hrms.dto.StaffPositionCreateDTO;
import uz.company.hrms.dto.StaffPositionResponseDTO;

import java.util.List;

public interface StaffPositionService {

    StaffPositionResponseDTO createStaffPosition(StaffPositionCreateDTO dto);

    StaffPositionResponseDTO updateStaffPosition(Long id,StaffPositionCreateDTO dto);

    StaffPositionResponseDTO getById(Long id);

    List<StaffPositionResponseDTO> getAll();

    void deleteStaffPosition(Long id);


}
