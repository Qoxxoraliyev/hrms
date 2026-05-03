package uz.company.hrms.service;

import uz.company.hrms.dto.VacationScheduleCreateDTO;
import uz.company.hrms.dto.VacationScheduleResponseDTO;

import java.util.List;

public interface VacationScheduleService {

    VacationScheduleResponseDTO createVacationSchedule(VacationScheduleCreateDTO dto);

    List<VacationScheduleResponseDTO> getAll();

    void delete(Long id);

    List<VacationScheduleResponseDTO> filterByDepartmentName(String name);

}
