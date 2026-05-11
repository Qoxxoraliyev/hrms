package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.DepartmentCreateDTO;
import uz.company.hrms.dto.DepartmentResponseDTO;

import uz.company.hrms.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody DepartmentCreateDTO dto){
        return departmentService.departmentCreate(dto);
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAll(){
        return departmentService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        departmentService.delete(id);
    }


}
