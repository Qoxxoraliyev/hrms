package uz.company.hrms.dto;

import uz.company.hrms.entity.Department;
import uz.company.hrms.enums.RetirementReason;

import java.time.LocalDate;

public class PensionerCreateDTO {

    String fullName;
    LocalDate birthDate;
    LocalDate retirementDate;
    Long departmentId;
    String phoneNumber;
    RetirementReason retirementReason;
    Long employeeId;
}
