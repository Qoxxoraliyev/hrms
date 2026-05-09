package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.SickLeaveCreateDTO;
import uz.company.hrms.dto.SickLeaveResponseDTO;
import uz.company.hrms.service.SickLeaveService;

import java.util.List;

@RestController
@RequestMapping("/api/sick-leave")
public class SickLeaveController {

    private final SickLeaveService sickLeaveService;

    public SickLeaveController(SickLeaveService sickLeaveService) {
        this.sickLeaveService = sickLeaveService;
    }

    @PostMapping
    public SickLeaveResponseDTO createSickLeave(@RequestBody SickLeaveCreateDTO dto){
        return sickLeaveService.createSickLeave(dto);
    }

    @GetMapping
    public List<SickLeaveResponseDTO> getAll(){
        return sickLeaveService.getAll();
    }

    @GetMapping("/employee/name")
    public List<SickLeaveResponseDTO> getByEmployeeFullName(@RequestParam String employeeFullName){
        return sickLeaveService.filterByEmployeeFullName(employeeFullName);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        sickLeaveService.delete(id);
    }



}
