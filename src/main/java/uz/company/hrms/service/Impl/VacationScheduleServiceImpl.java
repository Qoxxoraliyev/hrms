package uz.company.hrms.service.Impl;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.VacationEmployeeDTO;
import uz.company.hrms.dto.VacationScheduleArchiveDTO;
import uz.company.hrms.dto.VacationScheduleCreateDTO;
import uz.company.hrms.dto.VacationScheduleResponseDTO;
import uz.company.hrms.entity.Department;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.entity.VacationSchedule;
import uz.company.hrms.entity.VacationScheduleArchive;
import uz.company.hrms.enums.VacationMonth;
import uz.company.hrms.mapper.VacationScheduleMapper;
import uz.company.hrms.repository.DepartmentRepository;
import uz.company.hrms.repository.EmployeeRepository;
import uz.company.hrms.repository.VacationScheduleArchiveRepository;
import uz.company.hrms.repository.VacationScheduleRepository;
import uz.company.hrms.service.VacationScheduleService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class VacationScheduleServiceImpl implements VacationScheduleService {

    private final VacationScheduleRepository vacationScheduleRepository;

    private final VacationScheduleArchiveRepository archiveRepository;

    private final DepartmentRepository departmentRepository;

    private final EmployeeRepository employeeRepository;

    public VacationScheduleServiceImpl(VacationScheduleRepository vacationScheduleRepository, VacationScheduleArchiveRepository archiveRepository, DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.vacationScheduleRepository = vacationScheduleRepository;
        this.archiveRepository = archiveRepository;
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public VacationScheduleResponseDTO createVacationSchedule(VacationScheduleCreateDTO dto){

        Department department=departmentRepository.findByName(dto.departmentName())
                .orElseThrow(()->new RuntimeException("Department not found"));

        Employee employee=employeeRepository.findByFullName(dto.employeeName())
                .orElseThrow(()->new RuntimeException("Employee not found"));

        VacationSchedule vacationSchedule=new VacationSchedule();
        vacationSchedule.setVacationMonth(dto.vacationMonth());
        vacationSchedule.setDepartment(department);
        vacationSchedule.setEmployee(employee);
        VacationSchedule saved=vacationScheduleRepository.save(vacationSchedule);
        return VacationScheduleMapper.toDto(saved);
    }



    @Scheduled(cron = "0 59 23 31 12 *")
    public void moveAllToArchive(){
        List<VacationSchedule> schedules=vacationScheduleRepository.findAll();

        for (VacationSchedule schedule:schedules){
            VacationScheduleArchive archive=new VacationScheduleArchive();
            archive.setOriginalScheduleId(schedule.getId());
            archive.setEmployee(schedule.getEmployee());
            archive.setDepartment(schedule.getDepartment());
            archive.setVacationMonth(schedule.getVacationMonth());
            archive.setArchivedAt(LocalDate.now());
            archiveRepository.save(archive);
        }
        vacationScheduleRepository.deleteAll();

    }


    @Override
    @Transactional
    public List<VacationScheduleResponseDTO> getAll(){
        return vacationScheduleRepository.findAll()
                .stream()
                .map(VacationScheduleMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public List<VacationScheduleResponseDTO> filterByDepartmentName(String name){
        return vacationScheduleRepository.findByDepartment_Name(name)
                .stream()
                .map(VacationScheduleMapper::toDto)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<VacationEmployeeDTO> getCurrentMonthVacationEmployees(){
        VacationMonth currentMonth=VacationMonth.from(LocalDate.now().getMonth());
        return vacationScheduleRepository.findByVacationMonth(currentMonth)
                .stream()
                .map(v->new VacationEmployeeDTO(
                        v.getEmployee().getId(),
                        v.getEmployee().getFullName()
                ))
                .toList();
    }


    @Override
    @Transactional
    public List<VacationScheduleArchiveDTO> getArchiveVacationSchedule(){
        return archiveRepository.findAll()
                .stream()
                .map(archive->new VacationScheduleArchiveDTO(
                        archive.getId(),
                        archive.getEmployee().getFullName(),
                        archive.getDepartment().getName(),
                        archive.getVacationMonth(),
                        archive.getArchivedAt()
                )).toList();
    }




    @Override
    @Transactional
    public void delete(Long id){
        VacationSchedule vacationSchedule=vacationScheduleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Vacation not found"));
        vacationScheduleRepository.delete(vacationSchedule);
    }


}
