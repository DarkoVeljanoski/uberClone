package project.uberclone.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import project.uberclone.model.entity.Driver;
import project.uberclone.service.DriverService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private DriverService driverService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Driver driver = driverService.findDriverByEmail(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        return new User(driver.getEmail(), driver.getPassword(), authorities);
    }
}
