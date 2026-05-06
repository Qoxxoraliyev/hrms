package uz.company.hrms.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.PunishmentCreateDTO;
import uz.company.hrms.dto.PunishmentResponseDTO;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.entity.Punishment;
import uz.company.hrms.mapper.PunishmentMapper;
import uz.company.hrms.repository.EmployeeRepository;
import uz.company.hrms.repository.PunishmentRepository;
import uz.company.hrms.service.PunishmentService;

import java.util.List;


@Service
@Transactional
public class PunishmentServiceImpl implements PunishmentService {

    private final PunishmentRepository punishmentRepository;
    private final EmployeeRepository employeeRepository;

    public PunishmentServiceImpl(PunishmentRepository punishmentRepository, EmployeeRepository employeeRepository) {
        this.punishmentRepository = punishmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public PunishmentResponseDTO createPunishment(PunishmentCreateDTO dto){

        Employee employee=employeeRepository.findByName(dto.employeeFullName())
                .orElseThrow(()->new RuntimeException("Employee not found"));
        Punishment punishment=new Punishment();
        punishment.setPunishmentCount(dto.punishmentCount());
        punishment.setPunishmentType(dto.punishmentType());
        punishment.setEmployee(employee);
        punishment.setPunishmentDate(dto.punishmentDate());
        Punishment saved=punishmentRepository.save(punishment);
        return PunishmentMapper.toDTO(saved);
    }


    @Override
    @Transactional
    public List<PunishmentResponseDTO> getAll(){
        return punishmentRepository.findAll()
                .stream()
                .map(PunishmentMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id){
        Punishment punishment=punishmentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Punishment not found"));
        punishmentRepository.delete(punishment);
    }

    @Override
    @Transactional
    public List<PunishmentResponseDTO> filterByEmployeeFullName(String employeeFullName){
        return punishmentRepository.findByEmployee_Name(employeeFullName)
                .stream()
                .map(PunishmentMapper::toDTO)
                .toList();
    }


}
