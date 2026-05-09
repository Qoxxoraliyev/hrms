package uz.company.hrms.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.company.hrms.entity.User;
import java.util.Collection;
import java.util.List;

public class UsersDetails implements UserDetails {

    private final String email;
    private final String password;
    private final List<GrantedAuthority> authorities;

    public UsersDetails(User user){
        this.email=user.getEmail();
        this.password=user.getPassword();
        this.authorities=List.of(
                new SimpleGrantedAuthority(user.getRole().name())
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername(){
        return email;
    }

}
