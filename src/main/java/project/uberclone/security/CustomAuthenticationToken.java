package project.uberclone.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import project.uberclone.model.dto.LoggedUserDto;

import java.util.Collection;

public class CustomAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private LoggedUserDto loggedUserDto;

    public CustomAuthenticationToken(
            LoggedUserDto loggedUserDto, Collection<? extends GrantedAuthority> authorities) {
        super(loggedUserDto.getUsername(), loggedUserDto.getPassword(), authorities);
        this.loggedUserDto = loggedUserDto;
    }

    @Override
    public LoggedUserDto getPrincipal() {
        return loggedUserDto;
    }
}
