package uz.company.hrms.service.Impl;

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
