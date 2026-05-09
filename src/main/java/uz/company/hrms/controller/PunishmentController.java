package uz.company.hrms.controller;


import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.EmployeeNameDTO;
import uz.company.hrms.dto.PunishmentCreateDTO;
import uz.company.hrms.dto.PunishmentResponseDTO;
import uz.company.hrms.service.PunishmentService;

import java.util.List;

@RestController
@RequestMapping("/api/punishments")
public class PunishmentController {


    private final PunishmentService punishmentService;

    public PunishmentController(PunishmentService punishmentService) {
        this.punishmentService = punishmentService;
    }

    @PostMapping
    public PunishmentResponseDTO createPunishment(@RequestBody PunishmentCreateDTO dto){
        return punishmentService.createPunishment(dto);
    }

    @GetMapping
    public List<PunishmentResponseDTO> getAll(){
        return punishmentService.getAll();
    }

    @GetMapping("/employee/name")
    public List<PunishmentResponseDTO> getByEmployeeFullName(@RequestParam String employeeFullName){
        return punishmentService.filterByEmployeeFullName(employeeFullName);
    }

    @GetMapping("/employees")
    public List<EmployeeNameDTO> getEmployees(){
        return punishmentService.getEmployees();
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        punishmentService.delete(id);
    }



}
