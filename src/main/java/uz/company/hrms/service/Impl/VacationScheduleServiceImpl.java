package uz.company.hrms.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.VacationScheduleCreateDTO;
import uz.company.hrms.dto.VacationScheduleResponseDTO;
import uz.company.hrms.entity.VacationSchedule;
import uz.company.hrms.mapper.VacationScheduleMapper;
import uz.company.hrms.repository.VacationScheduleArchiveRepository;
import uz.company.hrms.repository.VacationScheduleRepository;
import uz.company.hrms.service.VacationScheduleService;

import java.util.List;

@Service
@Transactional
public class VacationScheduleServiceImpl implements VacationScheduleService {

    private final VacationScheduleRepository vacationScheduleRepository;

    private final VacationScheduleArchiveRepository vacationScheduleArchiveRepository;

    public VacationScheduleServiceImpl(VacationScheduleRepository vacationScheduleRepository, VacationScheduleArchiveRepository vacationScheduleArchiveRepository) {
        this.vacationScheduleRepository = vacationScheduleRepository;
        this.vacationScheduleArchiveRepository = vacationScheduleArchiveRepository;
    }

    @Override
    @Transactional
    public VacationScheduleResponseDTO createVacationSchedule(VacationScheduleCreateDTO dto){

        VacationSchedule vacationSchedule=new VacationSchedule();
        vacationSchedule.setVacationMonth(dto.vacationMonth());
        vacationSchedule.setDepartment(dto.department());
        vacationSchedule.setEmployee(dto.employee());
        VacationSchedule saved=vacationScheduleRepository.save(vacationSchedule);
        return VacationScheduleMapper.toDto(saved);
    }



    public void moveAllToArchive(){
        List<VacationSchedule> schedules=vacationScheduleRepository.findAll();

    }


}
