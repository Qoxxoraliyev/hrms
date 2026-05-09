package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.AwardCreateDTO;
import uz.company.hrms.dto.AwardResponseDTO;
import uz.company.hrms.dto.EmployeeNameDTO;
import uz.company.hrms.service.AwardService;

import java.util.List;

@RestController
@RequestMapping("/api/awards")
public class AwardController {

    private final AwardService awardService;

    public AwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @PostMapping
    public AwardResponseDTO createAward(AwardCreateDTO dto){
        return awardService.createAward(dto);
    }

    @GetMapping
    public List<AwardResponseDTO> getAll(){
        return awardService.getAll();
    }


    @GetMapping("/employee/name")
    public List<AwardResponseDTO> findByEmployee(@RequestParam String employeeName){
        return awardService.filterByEmployeeName(employeeName);
    }


    @GetMapping("/employees")
    public List<EmployeeNameDTO> getEmployees(){
        return awardService.getEmployees();
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        awardService.delete(id);
    }




}
