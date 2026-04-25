package uz.company.hrms.service;

import uz.company.hrms.dto.UserCreateDTO;
import uz.company.hrms.dto.UserResponseDTO;

import java.util.List;

public interface UserService {

    UserResponseDTO userRegister(UserCreateDTO dto);

    List<UserResponseDTO> getAll(String role);

    void delete(Long id);

}
