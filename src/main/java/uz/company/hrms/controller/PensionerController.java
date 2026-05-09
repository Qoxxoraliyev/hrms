package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.PensionerCreateDTO;
import uz.company.hrms.dto.PensionerResponseDTO;
import uz.company.hrms.service.PensionerService;

import java.util.List;

@RestController
@RequestMapping("/api/pensioners")
public class PensionerController {

    private final PensionerService pensionerService;

    public PensionerController(PensionerService pensionerService) {
        this.pensionerService = pensionerService;
    }

    @PostMapping
    public PensionerResponseDTO createPensioner(@RequestBody PensionerCreateDTO dto){
        return pensionerService.createPensioner(dto);
    }

    @GetMapping
    public List<PensionerResponseDTO> getAll(){
        return pensionerService.getAll();
    }


    @GetMapping("/department")
    public List<PensionerResponseDTO> getByDepartment(@RequestParam String departmentName){
        return pensionerService.filterByDepartmentName(departmentName);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        pensionerService.delete(id);
    }


}
