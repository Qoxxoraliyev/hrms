package uz.company.hrms.controller;

import org.springframework.web.bind.annotation.*;
import uz.company.hrms.dto.UserCreateDTO;
import uz.company.hrms.dto.UserResponseDTO;
import uz.company.hrms.dto.auth.UserPasswordUpdateDTO;
import uz.company.hrms.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserCreateDTO dto){
        return userService.userRegister(dto);
    }

    @GetMapping
    public List<UserResponseDTO> getAll(@RequestParam(required = false) String role){
        return userService.getAll(role);
    }

    @PatchMapping("/{id}/password")
    public void updatePassword(@PathVariable Long id,
                               @RequestBody UserPasswordUpdateDTO dto){
        userService.updatePassword(id,dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }


}
