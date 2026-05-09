package uz.company.hrms.security;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uz.company.hrms.entity.User;
import uz.company.hrms.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UsersDetails loadUserByUsername(String email){
        User user=userRepository.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found"));
        return new UsersDetails(user);
    }

}
