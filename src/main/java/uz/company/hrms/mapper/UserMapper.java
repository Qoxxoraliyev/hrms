package uz.company.hrms.mapper;

import uz.company.hrms.dto.UserCreateDTO;
import uz.company.hrms.dto.UserResponseDTO;
import uz.company.hrms.entity.User;

public class UserMapper {

    // DTO -> Entity
    public static User toEntity(UserCreateDTO dto){
        User user=new User();
        user.setFullName(dto.fullName());
        user.setEmail(dto.email());
        user.setRole(dto.role());
        return user;
    }


    // Entity -> DTO
    public static UserResponseDTO toDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getFullName(),
                user.getRole()
        );
    }


}
