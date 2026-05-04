package uz.company.hrms.mapper;

import uz.company.hrms.dto.PensionerResponseDTO;
import uz.company.hrms.entity.Pensioner;


public class PensionerMapper {

    public static PensionerResponseDTO toDTO(Pensioner pensioner){
        return new PensionerResponseDTO(
                pensioner.getId(),
                pensioner.getFullName(),
                pensioner.getBirthDate(),
                pensioner.getRetirementDate(),
                pensioner.getDepartment().getName(),
                pensioner.getPhoneNumber(),
                pensioner.getRetirementReason()
        );
    }


}
