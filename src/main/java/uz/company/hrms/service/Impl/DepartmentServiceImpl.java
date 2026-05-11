package uz.company.hrms.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.DepartmentCreateDTO;
import uz.company.hrms.dto.DepartmentResponseDTO;
import uz.company.hrms.entity.Department;
import uz.company.hrms.mapper.DepartmentMapper;
import uz.company.hrms.repository.DepartmentRepository;
import uz.company.hrms.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public DepartmentResponseDTO departmentCreate(DepartmentCreateDTO dto){
        Department department=new Department();
        department.setName(dto.name());
        Department saved=departmentRepository.save(department);
        return DepartmentMapper.toDTO(saved);
    }


    @Override
    @Transactional
    public List<DepartmentResponseDTO> getAll(){
        return departmentRepository.findAll()
                .stream()
                .map(DepartmentMapper::toDTO)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id){
        Department department=departmentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Department not found"));
        departmentRepository.delete(department);
    }




}
