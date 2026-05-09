package uz.company.hrms.service;

import uz.company.hrms.dto.StaffPositionCreateDTO;
import uz.company.hrms.dto.StaffPositionResponseDTO;

public interface StaffPositionService {

    StaffPositionResponseDTO createStaffPosition(StaffPositionCreateDTO dto);


}
