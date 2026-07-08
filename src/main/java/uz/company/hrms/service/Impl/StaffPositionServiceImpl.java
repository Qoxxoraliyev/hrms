package uz.company.hrms.service.Impl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.StaffPositionCreateDTO;
import uz.company.hrms.dto.StaffPositionResponseDTO;
import uz.company.hrms.dto.VacancyResponseDTO;
import uz.company.hrms.entity.Department;
import uz.company.hrms.entity.StaffPosition;
import uz.company.hrms.mapper.StaffPositionMapper;
import uz.company.hrms.repository.DepartmentRepository;
import uz.company.hrms.repository.StaffPositionRepository;
import uz.company.hrms.service.StaffPositionService;
import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public StaffPositionResponseDTO getById(Long id){
        StaffPosition staffPosition=staffPositionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Staff position not found"));
        return StaffPositionMapper.toDTO(staffPosition);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StaffPositionResponseDTO> getAll(){
        return staffPositionRepository.findAll()
                .stream()
                .map(StaffPositionMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public StaffPositionResponseDTO updateStaffPosition(Long id,StaffPositionCreateDTO dto){
        StaffPosition staffPosition=staffPositionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Staff position not found"));

        Department department=departmentRepository.findByName(dto.departmentName())
                .orElseThrow(()->new RuntimeException("Department not found"));

        if (dto.totalSlots()<staffPosition.getOccupiedSlots()){
            throw new RuntimeException("Total slots occupied slots dan kichik bo'lishi mumkin");
        }

        staffPosition.setPositionName(dto.positionName());
        staffPosition.setDepartment(department);
        staffPosition.setTotalSlots(dto.totalSlots());
        return StaffPositionMapper.toDTO(staffPosition);
    }


    @Override
    @Transactional
    public void deleteStaffPosition(Long id){
        StaffPosition staffPosition=staffPositionRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Staff position not found"));

        if (staffPosition.getOccupiedSlots()>0){
            throw new RuntimeException(
                    "Bu shtatda xodim ishlayotganligi sababli o'chirib bo'lmaydi.");
        }
        staffPositionRepository.delete(staffPosition);
    }


    @Override
    @Transactional(readOnly = true)
    public List<VacancyResponseDTO> getVacantPositions(){
        return staffPositionRepository.findVacantPositions()
                .stream()
                .map(staff->new VacancyResponseDTO(
                        staff.getDepartment().getName(),
                        staff.getPositionName(),
                        staff.getTotalSlots(),
                        staff.getOccupiedSlots(),
                        staff.getTotalSlots()-staff.getOccupiedSlots()
                ))
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<VacancyResponseDTO> getVacantPositionsByDepartment(String departmentName){
        return staffPositionRepository.findVacantPositionsByDepartment(departmentName)
                .stream()
                .map(staff->new VacancyResponseDTO(
                        staff.getDepartment().getName(),
                        staff.getPositionName(),
                        staff.getTotalSlots(),
                        staff.getOccupiedSlots(),
                        staff.getTotalSlots()-staff.getOccupiedSlots()
                ))
                .toList();
    }



    @Override
    @Transactional(readOnly = true)
    public List<VacancyResponseDTO> getVacantPositionsByPosition(String positionName){
        return staffPositionRepository.findVacantPositionByPosition(positionName)
                .stream()
                .map(staff->new VacancyResponseDTO(
                        staff.getDepartment().getName(),
                        staff.getPositionName(),
                        staff.getTotalSlots(),
                        staff.getOccupiedSlots(),
                        staff.getTotalSlots()-staff.getOccupiedSlots()
                ))
                .toList();
    }



    @Override
    @Transactional(readOnly = true)
    public List<VacancyResponseDTO> getFullPositions(){
        return staffPositionRepository.findFullPositions()
                .stream()
                .map(staff->new VacancyResponseDTO(
                        staff.getDepartment().getName(),
                        staff.getPositionName(),
                        staff.getTotalSlots(),
                        staff.getOccupiedSlots(),
                        0
                ))
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public List<VacancyResponseDTO> getFullPositionsByDepartment(String departmentName){
        return staffPositionRepository.findFullPositionsByDepartment(departmentName)
                .stream()
                .map(staff->new VacancyResponseDTO(
                        staff.getDepartment().getName(),
                        staff.getPositionName(),
                        staff.getTotalSlots(),
                        staff.getOccupiedSlots(),
                        0
                ))
                .toList();
    }





    @Override
    @Transactional(readOnly = true)
    public List<VacancyResponseDTO> getFullPositionsByPosition(String positionName){
        return staffPositionRepository.findFullPositionsByPosition(positionName)
                .stream()
                .map(staff->new VacancyResponseDTO(
                        staff.getDepartment().getName(),
                        staff.getPositionName(),
                        staff.getTotalSlots(),
                        staff.getOccupiedSlots(),
                        0
                ))
                .toList();
    }





}
