package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.VacationEmployeeDTO;
import uz.company.hrms.dto.VacationScheduleCreateDTO;
import uz.company.hrms.dto.VacationScheduleResponseDTO;
import uz.company.hrms.service.VacationScheduleService;

import java.util.List;

@RestController
@RequestMapping("/api/vacation-schedule")
public class VacationScheduleController {

    private final VacationScheduleService vacationSchedule;

    public VacationScheduleController(VacationScheduleService vacationSchedule) {
        this.vacationSchedule = vacationSchedule;
    }

    @PostMapping
    public VacationScheduleResponseDTO createVacationSchedule(@RequestBody VacationScheduleCreateDTO dto){
        return vacationSchedule.createVacationSchedule(dto);
    }

    @GetMapping
    public List<VacationScheduleResponseDTO> getAll(){
        return vacationSchedule.getAll();
    }

    @GetMapping("/department")
    public List<VacationScheduleResponseDTO> getByDepartment(@RequestParam String departmentName){
        return vacationSchedule.filterByDepartmentName(departmentName);
    }

    @GetMapping("/current-month")
    public List<VacationEmployeeDTO> getCurrentMonthVacationEmployees(){
        return vacationSchedule.getCurrentMonthVacationEmployees();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        vacationSchedule.delete(id);
    }


    }
