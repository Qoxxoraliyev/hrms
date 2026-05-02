package uz.company.hrms.service.Impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.EmployeeCreateDTO;
import uz.company.hrms.dto.EmployeeResponseDTO;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.enums.NextAttestation;
import uz.company.hrms.enums.Rank;
import uz.company.hrms.mapper.EmployeeMapper;
import uz.company.hrms.repository.EmployeeRepository;
import uz.company.hrms.service.EmployeeService;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO employeeCreateDTO(EmployeeCreateDTO dto){

        Employee employee=new Employee();

        employee.setFullName(dto.fullName());
        employee.setRank(dto.rank());
        employee.setDepartment(dto.department());
        employee.setPosition(dto.position());
        employee.setBirthDate(dto.birthDate());
        employee.setAddress(dto.address());
        employee.setEmploymentDate(dto.employmentDate());
        employee.setRankAssignedDate(dto.rankAssignedDate());
        employee.setAwardCountFromOffice(dto.awardCountFromOffice());
        employee.setAppreciationCountFromTashkent(dto.appreciationCountFromTashkent());

        LocalDate nextDate=calculateNextAttestationDate(
                dto.rank(),
                dto.rankAssignedDate()
        );

        employee.setNextAttestationDate(nextDate);

        if (nextDate==null){
            employee.setNextAttestationStatus(NextAttestation.MUDDATSIZ);
        }
        Employee saved=employeeRepository.save(employee);

        return EmployeeMapper.toDTO(saved);
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> filterByAge(Integer minAge,Integer maxAge){
        LocalDate today=LocalDate.now();
        List<Employee> employees;

        if (minAge!=null && maxAge==null){
            LocalDate limitDate=today.minusYears(minAge);

            employees=employeeRepository.findByBirthDateAfter(limitDate);

        } else if (minAge!=null && maxAge!=null) {
            LocalDate olderThanDate=today.minusYears(minAge);
            LocalDate youngerThanDate=today.minusYears(maxAge);

            employees=employeeRepository.findByBirthDateBetween(
                    youngerThanDate,
                    olderThanDate
            );
        }else {
            employees=employeeRepository.findAll();
        }

        return employees.stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> filter(
            Rank rank,
            Long departmentId,
            Long positionId,
            Integer minExperience,
            Integer maxExperience
    ){
        LocalDate today=LocalDate.now();
        Specification<Employee> spec = (root, query, cb) -> cb.conjunction();
        if (rank!=null){
            spec=spec.and((root, query, cb) ->
                     cb.equal(root.get("rank"),rank)
                    );
        }


        if (departmentId!=null){
            spec=spec.and(((root, query, cb) ->
                    cb.equal(root.get("department").get("id"),departmentId)
                    ));
        }

        if (positionId!=null){
            spec=spec.and((root, query, cb) ->
                    cb.equal(root.get("position").get("id"),positionId)
                    );
        }

        if (minExperience!=null && maxExperience==null){
            LocalDate date=today.minusYears(minExperience);

            spec=spec.and(((root, query, cb) ->
                    cb.lessThanOrEqualTo(root.get("employmentDate"),date)
                    ));
        }

        if (minExperience!=null && maxExperience!=null){
            LocalDate maxDate=today.minusYears(minExperience);
            LocalDate minDate=today.minusYears(maxExperience);

            spec=spec.and((root, query, cb) ->
                    cb.between(root.get("employmentDate"),minDate,maxDate)
                    );
        }

        return employeeRepository.findAll(spec)
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }



    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAll(){
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id){
        Employee employee=employeeRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Employee not found with id: "+id));
        employeeRepository.delete(employee);
    }

    private LocalDate calculateNextAttestationDate(Rank rank, LocalDate rankAssignedDate) {

        return switch (rank) {

            case KICHIK_SERJANT,
                 SERJANT,
                 KATTA_SERJANT,
                 POLKOVNIK -> null;

            case LEYTENANT,
                 KATTA_LEYTENANT -> rankAssignedDate.plusYears(2);

            case KAPITAN -> rankAssignedDate.plusYears(3);

            case MAYOR -> rankAssignedDate.plusYears(4);

            case PODPOLKOVNIK -> rankAssignedDate.plusYears(5);
        };
    }

}
