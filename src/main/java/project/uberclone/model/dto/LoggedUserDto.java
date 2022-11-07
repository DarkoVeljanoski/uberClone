package project.uberclone.model.dto;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import project.uberclone.model.entity.DriverStatusEnum;

import java.util.Collection;

@Getter
public class LoggedUserDto extends User {

    private Long id;
    private String name;
    private String lastname;

    public LoggedUserDto(Long id,String name,
                           String lastname, String email,
                           String password, boolean enabled, boolean accountNonExpired,
                           boolean credentialsNonExpired, boolean accountNonLocked,
                           Collection<? extends GrantedAuthority> authorities
                           )

    {
        super(email,
                password,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities);
        this.id = id;
        this.name = name;
        this.lastname = lastname;

    }
}
