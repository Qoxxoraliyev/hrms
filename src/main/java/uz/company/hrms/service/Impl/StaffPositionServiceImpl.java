package uz.company.hrms.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.StaffPositionCreateDTO;
import uz.company.hrms.dto.StaffPositionResponseDTO;
import uz.company.hrms.entity.Department;
import uz.company.hrms.entity.StaffPosition;
import uz.company.hrms.mapper.StaffPositionMapper;
import uz.company.hrms.repository.DepartmentRepository;
import uz.company.hrms.repository.StaffPositionRepository;
import uz.company.hrms.service.StaffPositionService;

@Service
@Transactional
public class StaffPositionServiceImpl implements StaffPositionService {

    private final StaffPositionRepository staffPositionRepository;
    private final DepartmentRepository departmentRepository;

    public StaffPositionServiceImpl(StaffPositionRepository staffPositionRepository, DepartmentRepository departmentRepository) {
        this.staffPositionRepository = staffPositionRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    @Transactional
    public StaffPositionResponseDTO createStaffPosition(StaffPositionCreateDTO dto){

        Department department=departmentRepository.findByName(dto.departmentName())
                .orElseThrow(()->new RuntimeException("Department not found"));

        StaffPosition staffPosition=new StaffPosition();
        staffPosition.setPositionName(dto.positionName());
        staffPosition.setOccupiedSlots(dto.occupiedSlots());
        staffPosition.setTotalSlots(dto.totalSlots());
        staffPosition.setDepartment(department);
        StaffPosition saved=staffPositionRepository.save(staffPosition);
        return StaffPositionMapper.toDTO(saved);
    }


}
