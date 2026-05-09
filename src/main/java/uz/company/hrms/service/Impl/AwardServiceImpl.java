package uz.company.hrms.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.AwardCreateDTO;
import uz.company.hrms.dto.AwardResponseDTO;
import uz.company.hrms.dto.EmployeeNameDTO;
import uz.company.hrms.entity.Award;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.mapper.AwardMapper;
import uz.company.hrms.repository.AwardRepository;
import uz.company.hrms.repository.EmployeeRepository;
import uz.company.hrms.service.AwardService;

import java.util.List;

@Service
@Transactional
public class AwardServiceImpl implements AwardService {

    private final AwardRepository awardRepository;
    private final EmployeeRepository employeeRepository;

    public AwardServiceImpl(AwardRepository awardRepository, EmployeeRepository employeeRepository) {
        this.awardRepository = awardRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public AwardResponseDTO createAward(AwardCreateDTO dto){

        Employee employee=employeeRepository.findByFullName(dto.employeeFullName())
                .orElseThrow(()->new RuntimeException("Employee not found"));

        Award award=new Award();
        award.setEmployee(employee);
        award.setAwardCount(dto.awardCount());
        award.setAwardType(dto.awardType());
        award.setAwardDate(dto.awardDate());
        Award saved=awardRepository.save(award);
        return AwardMapper.toDTO(saved);
    }


    @Override
    @Transactional
    public List<AwardResponseDTO> getAll(){
        return awardRepository.findAll()
                .stream()
                .map(AwardMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public List<AwardResponseDTO> filterByEmployeeName(String employeeFullName){
        return awardRepository.findByEmployee_FullName(employeeFullName)
                .stream()
                .map(AwardMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public List<EmployeeNameDTO> getEmployees(){
        return employeeRepository.findAll()
                .stream()
                .map(employee -> new EmployeeNameDTO(
                        employee.getId(),
                        employee.getFullName()
                ))
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id){
        Award award=awardRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Award not found"));

        awardRepository.delete(award);
    }


}
