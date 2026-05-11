package uz.company.hrms.service.Impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.EmployeeNameDTO;
import uz.company.hrms.dto.SickLeaveArchiveDTO;
import uz.company.hrms.dto.SickLeaveCreateDTO;
import uz.company.hrms.dto.SickLeaveResponseDTO;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.entity.SickLeave;
import uz.company.hrms.entity.SickLeaveArchive;
import uz.company.hrms.mapper.SickLeaveMapper;
import uz.company.hrms.repository.EmployeeRepository;
import uz.company.hrms.repository.SickLeaveArchiveRepository;
import uz.company.hrms.repository.SickLeaveRepository;
import uz.company.hrms.service.SickLeaveService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class SickLeaveServiceImpl implements SickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;
    private final EmployeeRepository employeeRepository;
    private final SickLeaveArchiveRepository sickLeaveArchiveRepository;

    public SickLeaveServiceImpl(SickLeaveRepository sickLeaveRepository, EmployeeRepository employeeRepository, SickLeaveArchiveRepository sickLeaveArchiveRepository) {
        this.sickLeaveRepository = sickLeaveRepository;
        this.employeeRepository = employeeRepository;
        this.sickLeaveArchiveRepository = sickLeaveArchiveRepository;
    }

    @Override
    @Transactional
    public SickLeaveResponseDTO createSickLeave(SickLeaveCreateDTO dto){

        Employee employee=employeeRepository.findByFullName(dto.employeeFullName())
                .orElseThrow(()->new RuntimeException("Employee not found"));

        SickLeave sickLeave=new SickLeave();
        sickLeave.setDays(dto.days());
        sickLeave.setEmployee(employee);
        sickLeave.setDiseaseName(dto.diseaseName());
        sickLeave.setStartDate(dto.startDate());
        sickLeave.setEndDate(dto.endDate());
        SickLeave saved=sickLeaveRepository.save(sickLeave);
        return SickLeaveMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public void delete(Long id){
        SickLeave sickLeave=sickLeaveRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Sick leave not found"));

        SickLeaveArchive archive=new SickLeaveArchive();
        archive.setOriginalId(sickLeave.getId());
        archive.setEmployee(sickLeave.getEmployee());
        archive.setStartDate(sickLeave.getStartDate());
        archive.setEndDate(sickLeave.getEndDate());
        archive.setDays(sickLeave.getDays());
        archive.setDiseaseName(sickLeave.getDiseaseName());
        archive.setArchivedAt(LocalDate.now());
        sickLeaveArchiveRepository.save(archive);
        sickLeaveRepository.delete(sickLeave);
    }


    @Override
    @Transactional
    public List<SickLeaveResponseDTO> filterByEmployeeFullName(String fullName){
        return sickLeaveRepository.findByEmployee_FullName(fullName)
                .stream()
                .map(SickLeaveMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<EmployeeNameDTO> getEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeNameDTO(
                        employee.getId(),
                        employee.getFullName()))
                .toList();
    }


    @Override
    @Transactional
    public List<SickLeaveArchiveDTO> getSickLeaveArchive(){
        return sickLeaveArchiveRepository.findAll()
                .stream()
                .map(sickLeaveArchive -> new SickLeaveArchiveDTO(
                        sickLeaveArchive.getId(),
                        sickLeaveArchive.getEmployee().getFullName(),
                        sickLeaveArchive.getStartDate(),
                        sickLeaveArchive.getEndDate(),
                        sickLeaveArchive.getDays(),
                        sickLeaveArchive.getDiseaseName(),
                        sickLeaveArchive.getYear(),
                        sickLeaveArchive.getArchivedAt()
                )).toList();
    }



    @Override
    @Transactional
    public List<SickLeaveResponseDTO> getAll(){
        return sickLeaveRepository.findAll()
                .stream()
                .map(SickLeaveMapper::toDTO)
                .toList();
    }


}
