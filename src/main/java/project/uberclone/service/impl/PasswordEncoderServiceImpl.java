package project.uberclone.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.uberclone.service.PasswordEncoderService;

@Service
@AllArgsConstructor
public class PasswordEncoderServiceImpl implements PasswordEncoderService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String encode(String passwordToEncode) {
        return bCryptPasswordEncoder.encode(passwordToEncode);
    }

    @Override
    public Boolean matches(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }
}
