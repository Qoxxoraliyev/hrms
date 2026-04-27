package uz.company.hrms.mapper;

import uz.company.hrms.dto.EmployeeCreateDTO;
import uz.company.hrms.dto.EmployeeResponseDTO;
import uz.company.hrms.entity.Employee;

public class EmployeeMapper {

    public static Employee toEntity(EmployeeCreateDTO dto){
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
        return employee;
    }

    public static EmployeeResponseDTO toDTO(Employee employee){
        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getFullName(),
                employee.getRank(),
                employee.getDepartment(),
                employee.getPosition(),
                employee.getBirthDate(),
                employee.getAddress(),
                employee.getEmploymentDate(),
                employee.getRankAssignedDate(),
                employee.getAwardCountFromOffice(),
                employee.getAppreciationCountFromTashkent(),
                employee.getNextAttestationDate(),
                employee.getNextAttestationStatus()
        );
    }


}
