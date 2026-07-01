package uz.company.hrms.service.Impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.*;
import uz.company.hrms.entity.Department;
import uz.company.hrms.entity.Employee;
import uz.company.hrms.entity.EmployeeArchive;
import uz.company.hrms.entity.StaffPosition;
import uz.company.hrms.enums.NextAttestation;
import uz.company.hrms.enums.Rank;
import uz.company.hrms.mapper.EmployeeMapper;
import uz.company.hrms.mapper.StaffTableMapper;
import uz.company.hrms.repository.*;
import uz.company.hrms.service.EmployeeService;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final EmployeeArchiveRepository archiveRepository;
    private final StaffPositionRepository staffPositionRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, EmployeeArchiveRepository archiveRepository, StaffPositionRepository staffPositionRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.archiveRepository = archiveRepository;
        this.staffPositionRepository = staffPositionRepository;
    }

    @Override
    @Transactional
    public EmployeeResponseDTO employeeCreateDTO(EmployeeCreateDTO dto){

        Department department=departmentRepository.findByName(dto.departmentName())
                .orElseThrow(()->new RuntimeException("Department not found"));

        StaffPosition staffPosition=staffPositionRepository.findByPositionNameAndDepartment_Name(
                dto.staffPositionName(),
                dto.departmentName()
        )
                .orElseThrow(()->new RuntimeException("Staff position not found"));

        if (!staffPosition.getDepartment().getId().equals(department.getId())){
            throw new RuntimeException("Staff position bu department ga tegishli emas");
        }

        if (staffPosition.getOccupiedSlots()>=staffPosition.getTotalSlots()){
            throw new RuntimeException("Bu shtat to'lgan");
        }

        Employee employee=new Employee();

        employee.setFullName(dto.fullName());
        employee.setRank(dto.rank());
        employee.setDepartment(department);
        employee.setStaffPosition(staffPosition);
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
        else {
            employee.setNextAttestationStatus(NextAttestation.MUDDATLI);
        }

        staffPosition.setOccupiedSlots(staffPosition.getOccupiedSlots()+1);

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
            String departmentName,
            String staffPositionName,
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


        if (departmentName!=null && !departmentName.isBlank()){
            spec=spec.and(((root, query, cb) ->
                    cb.equal(root.get("department").get("name"),departmentName)
                    ));
        }

        if (staffPositionName!=null && !staffPositionName.isBlank()){
            spec=spec.and((root, query, cb) ->
                    cb.equal(root.get("staffPosition").get("positionName"),staffPositionName)
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
    public List<StaffTableResponseDTO> getStaffTable(){
        return employeeRepository.findAll()
                .stream()
                .map(StaffTableMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffTableResponseDTO> getStaffTableByDepartment(String departmentName){
        return employeeRepository
                .findByDepartment_Name(departmentName)
                .stream()
                .map(StaffTableMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffTableResponseDTO> getStaffTableByName(String fullName){
        return employeeRepository
                .findByFullName(fullName)
                .stream()
                .map(StaffTableMapper::toDTO)
                .toList();
    }



    @Override
    @Transactional(readOnly = true)
    public List<StaffTableResponseDTO> getStaffTableByPosition(String positionName){
        return employeeRepository
                .findByStaffPosition_PositionName(positionName)
                .stream()
                .map(StaffTableMapper::toDTO)
                .toList();
    }



    @Override
    @Transactional
    public List<ArchiveEmployeeDTO> getArchiveEmployee(){
        return archiveRepository.findAll()
                .stream()
                .map(archive->new ArchiveEmployeeDTO(
                        archive.getId(),
                        archive.getFullName(),
                        archive.getBirthDate(),
                        archive.getEmploymentDate(),
                        archive.getLeavingDate(),
                        archive.getDepartmentName(),
                        archive.getPositionName(),
                        archive.getRetirementReason()
                ))
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
    public void deleteEmployee(EmployeeDeleteDTO dto){
        Employee employee=employeeRepository.findByFullName(dto.employeeFullName())
                .orElseThrow(()->new RuntimeException("Employee not found"));

        StaffPosition staffPosition=employee.getStaffPosition();

        if (staffPosition.getOccupiedSlots()>0){
            staffPosition.setOccupiedSlots(staffPosition.getOccupiedSlots()-1);
        }

        if (Boolean.TRUE.equals(dto.archive())){
            EmployeeArchive archive=new EmployeeArchive();
            archive.setOldEmployeeId(employee.getId());
            archive.setFullName(employee.getFullName());
            archive.setBirthDate(employee.getBirthDate());
            archive.setEmploymentDate(employee.getEmploymentDate());
            archive.setLeavingDate(employee.getEmploymentDate());
            archive.setDepartmentName(employee.getDepartment().getName());
            archive.setPositionName(employee.getStaffPosition().getPositionName());
            archive.setRetirementReason(dto.retirementReason());
            String experience=calculateExperience(employee.getEmploymentDate());
            archive.setExperience(experience);
            archiveRepository.save(archive);
        }

        employeeRepository.delete(employee);
    }


    @Override
    @Transactional(readOnly = true)
    public List<YoungEmployeeDTO> getYoungEmployee(){
        LocalDate twoYearsAgo=LocalDate.now().minusYears(2);
        return employeeRepository.findByEmploymentDateAfter(twoYearsAgo)
                .stream()
                .map(employee -> new YoungEmployeeDTO(
                        employee.getId(),
                        employee.getFullName(),
                        employee.getEmploymentDate()
                ))
                .toList();
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


    private String calculateExperience(LocalDate employmentDate){
        Period period=Period.between(employmentDate,LocalDate.now());
        return period.getYears()+"yil, "+period.getMonths()+" oy, "+period.getDays()+" kun";
    }


}
