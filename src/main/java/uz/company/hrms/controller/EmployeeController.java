package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.*;
import uz.company.hrms.enums.Rank;
import uz.company.hrms.service.EmployeeService;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Employee qo'shish
     * @return
     */
    @PostMapping
    public EmployeeResponseDTO createEmployee(@RequestBody EmployeeCreateDTO dto){
        return employeeService.employeeCreateDTO(dto);
    }


    /**
     * Barcha hodimlar ro'yhati
     */
    @GetMapping
    public List<EmployeeResponseDTO> getAll(){
        return employeeService.getAll();
    }

    /**
     * hodimni o'chirish
     */
    @DeleteMapping
    public void deleteEmployee(
            @RequestBody EmployeeDeleteDTO dto
            ){
        employeeService.deleteEmployee(dto);
    }

    /**
     * yosh bo'yicha filterlash
     */
    @GetMapping("/filter-age")
    public List<EmployeeResponseDTO> filterByAge(
            @RequestParam Integer minAge,
            @RequestParam Integer maxAge){
        return employeeService.filterByAge(minAge,maxAge);
    }

    /**
     * yosh xodimlar ro'yhati
     */
    @GetMapping("/young")
    public List<YoungEmployeeDTO> getYoungEmployee(){
        return employeeService.getYoungEmployee();
    }

    /**
     * arxivdagi xodimlarni ro'yhatini olish
     * @return
     */
    @GetMapping("/archive")
    public List<ArchiveEmployeeDTO> getArchiveEmployee(){
        return employeeService.getArchiveEmployee();
    }

    // lavozim va ism familiyasi chiqadi
    @GetMapping("/staff-table")
    public List<StaffTableResponseDTO> getStaffTable(){
        return employeeService.getStaffTable();
    }

    // bo'lim bo'yicha hodimni lavozimi va ism familiyasini chiqaradi
    @GetMapping("/staff-table/department")
    public List<StaffTableResponseDTO> getByDepartment(
            @RequestParam String departmentName
    ){
        return employeeService.getStaffTableByDepartment(departmentName);
    }



    @GetMapping("filter")
    public List<EmployeeResponseDTO> filter(
            @RequestParam(required = false) Rank rank,
            @RequestParam(required = false) String departmentName,
            @RequestParam(required = false) String staffPositionName,
            @RequestParam(required = false) Integer minExperience,
            @RequestParam(required = false) Integer maxExperience
            ){
        return employeeService.filter(
                rank,
                departmentName,
                staffPositionName,
                minExperience,
                maxExperience
        );
    }



}
