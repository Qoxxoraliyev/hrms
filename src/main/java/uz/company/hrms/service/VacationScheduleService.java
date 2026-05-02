package uz.company.hrms.service;

import uz.company.hrms.dto.VacationScheduleCreateDTO;
import uz.company.hrms.dto.VacationScheduleResponseDTO;

public interface VacationScheduleService {

    VacationScheduleResponseDTO createVacationSchedule(VacationScheduleCreateDTO dto);

}
