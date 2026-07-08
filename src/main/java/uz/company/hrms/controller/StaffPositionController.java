package uz.company.hrms.controller;
import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.StaffPositionCreateDTO;
import uz.company.hrms.dto.StaffPositionResponseDTO;
import uz.company.hrms.dto.VacancyResponseDTO;
import uz.company.hrms.service.StaffPositionService;

import java.util.List;

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


    @GetMapping("/vacancies")
    public List<VacancyResponseDTO> getVacantPositions(){
        return staffPositionService.getVacantPositions();
    }


    @GetMapping("/vacancies/department")
    public List<VacancyResponseDTO> getVacantPositionsByDepartment(
            @RequestParam String departmentName
    ){
        return staffPositionService.getVacantPositionsByDepartment(departmentName);
    }


    @GetMapping("/vacancies/position")
    public List<VacancyResponseDTO> getVacantPositionByPosition(
            @RequestParam String positionName
    ){
        return staffPositionService.getVacantPositionsByPosition(positionName);
    }


    @GetMapping("/full")
    public List<VacancyResponseDTO> getFullPositions(){
        return staffPositionService.getFullPositions();
    }


    @GetMapping("/full/department")
    public List<VacancyResponseDTO> getFullPositionsByDepartment(
            @RequestParam String departmentName){
        return staffPositionService.getFullPositionsByDepartment(departmentName);
    }


    @GetMapping("/full/position")
    public List<VacancyResponseDTO> getFullPositionsByPosition(
            @RequestParam String positionName
    ){
        return staffPositionService.getFullPositionsByPosition(positionName);
    }





}
