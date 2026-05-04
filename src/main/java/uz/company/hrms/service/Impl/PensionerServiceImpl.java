package uz.company.hrms.service.Impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.PensionerCreateDTO;
import uz.company.hrms.dto.PensionerResponseDTO;
import uz.company.hrms.entity.Department;
import uz.company.hrms.entity.Pensioner;
import uz.company.hrms.mapper.PensionerMapper;
import uz.company.hrms.repository.DepartmentRepository;
import uz.company.hrms.repository.PensionerRepository;
import uz.company.hrms.service.PensionerService;

import java.util.List;

@Service
@Transactional
public class PensionerServiceImpl implements PensionerService {

    private final PensionerRepository pensionerRepository;

    private final DepartmentRepository departmentRepository;

    public PensionerServiceImpl(PensionerRepository pensionerRepository, DepartmentRepository departmentRepository) {
        this.pensionerRepository = pensionerRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public PensionerResponseDTO createPensioner(PensionerCreateDTO dto){
        Department department=departmentRepository.findByName(dto.departmentName())
                .orElseThrow(()->new RuntimeException("Department not found"));

        Pensioner pensioner=new Pensioner();
        pensioner.setBirthDate(dto.birthDate());
        pensioner.setFullName(dto.fullName());
        pensioner.setPhoneNumber(dto.phoneNumber());
        pensioner.setRetirementReason(dto.retirementReason());
        pensioner.setDepartment(department);
        Pensioner saved=pensionerRepository.save(pensioner);
        return PensionerMapper.toDTO(saved);
    }


    @Override
    @Transactional
    public List<PensionerResponseDTO> getAll(){
        return pensionerRepository.findAll()
                .stream()
                .map(PensionerMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public List<PensionerResponseDTO> filterByDepartmentName(String departmentName){
        return pensionerRepository.findByDepartment_Name(departmentName)
                .stream()
                .map(PensionerMapper::toDTO)
                .toList();
    }c


    @Override
    @Transactional
    public void delete(Long id){
        Pensioner pensioner=pensionerRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Pensioner not found"));

        pensionerRepository.delete(pensioner);
    }






}
