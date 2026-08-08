package uz.company.hrms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.*;
import uz.company.hrms.entity.VacationSchedule;
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


    @PutMapping("/{id}")
    public ResponseEntity<VacationScheduleResponseDTO> update(
            @PathVariable Long id,
            @RequestBody VacationScheduleUpdateDTO dto
            ){
        VacationScheduleUpdateDTO updateDTO=new VacationScheduleUpdateDTO(
                id,
                dto.employeeName(),
                dto.departmentName(),
                dto.vacationMonth()
        );

        return ResponseEntity.ok(vacationSchedule.updateVacationSchedule(updateDTO));
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

    @GetMapping("/archive")
    public List<VacationScheduleArchiveDTO> getArchiveVacationSchedule(){
        return vacationSchedule.getArchiveVacationSchedule();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        vacationSchedule.delete(id);
    }


    }
