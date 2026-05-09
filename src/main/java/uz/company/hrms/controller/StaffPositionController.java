package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.company.hrms.dto.StaffPositionCreateDTO;
import uz.company.hrms.dto.StaffPositionResponseDTO;
import uz.company.hrms.service.StaffPositionService;

@RestController
@RequestMapping("/api/staff-position")
public class StaffPositionController {

    private final StaffPositionService staffPositionService;

    public StaffPositionController(StaffPositionService staffPositionService) {
        this.staffPositionService = staffPositionService;
    }

    @PostMapping
    public StaffPositionResponseDTO createStaffPosition(@RequestBody StaffPositionCreateDTO dto){
        return staffPositionService.createStaffPosition(dto);
    }


}
