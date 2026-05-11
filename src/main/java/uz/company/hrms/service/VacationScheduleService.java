package uz.company.hrms.service;

import uz.company.hrms.dto.VacationEmployeeDTO;
import uz.company.hrms.dto.VacationScheduleCreateDTO;
import uz.company.hrms.dto.VacationScheduleResponseDTO;
import uz.company.hrms.dto.VacationScheduleArchiveDTO;

import java.util.List;

public interface VacationScheduleService {

    VacationScheduleResponseDTO createVacationSchedule(VacationScheduleCreateDTO dto);

    List<VacationScheduleResponseDTO> getAll();

    void delete(Long id);

    List<VacationScheduleResponseDTO> filterByDepartmentName(String name);

    List<VacationEmployeeDTO> getCurrentMonthVacationEmployees();

    List<VacationScheduleArchiveDTO> getArchiveVacationSchedule();


}
