package uz.company.hrms.service;

import uz.company.hrms.dto.*;

import java.util.List;

public interface VacationScheduleService {

    VacationScheduleResponseDTO createVacationSchedule(VacationScheduleCreateDTO dto);

    List<VacationScheduleResponseDTO> getAll();

    void delete(Long id);

    List<VacationScheduleResponseDTO> filterByDepartmentName(String name);

    List<VacationEmployeeDTO> getCurrentMonthVacationEmployees();

    List<VacationScheduleArchiveDTO> getArchiveVacationSchedule();

    VacationScheduleResponseDTO updateVacationSchedule(VacationScheduleUpdateDTO dto);
}
