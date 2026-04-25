package uz.company.hrms.service.Impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.company.hrms.dto.UserCreateDTO;
import uz.company.hrms.dto.UserResponseDTO;
import uz.company.hrms.entity.User;
import uz.company.hrms.enums.Role;
import uz.company.hrms.mapper.UserMapper;
import uz.company.hrms.repository.UserRepository;
import uz.company.hrms.service.UserService;

import java.util.List;


@Service
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public UserResponseDTO userRegister(UserCreateDTO dto){

        if (userRepository.existsByEmail(dto.email())){
            throw new RuntimeException("Email already exists");
        }

        User user= UserMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.password()));
        User savedUser=userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }


    @Override
    @Transactional
    public List<UserResponseDTO> getAll(String role){

        List<User> users;

        if (role==null || role.isBlank()){
            users=userRepository.findAll();
        }
        else {
            Role userRole;
            try {
                userRole=Role.valueOf(role.toUpperCase());
            }catch (IllegalArgumentException e){
                throw new RuntimeException("Invalid role: "+role);
            }
            users=userRepository.findByRole(userRole);
        }
        return users.stream()
                .map(UserMapper::toDTO)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id){

        User user=userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("User not found: "+id));
        userRepository.delete(user);
    }



}
