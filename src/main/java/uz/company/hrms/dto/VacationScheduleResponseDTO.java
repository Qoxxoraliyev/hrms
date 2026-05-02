package uz.company.hrms.dto;
import uz.company.hrms.enums.VacationMonth;

public record VacationScheduleResponseDTO(
        Long id,
        String employeeFullName,
        String departmentName,
        VacationMonth vacationMonth
) {}
