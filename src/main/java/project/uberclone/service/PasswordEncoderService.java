package project.uberclone.service;

public interface PasswordEncoderService {
    String encode(String passwordToEncode);

    Boolean matches(CharSequence rawPassword, String encodedPassword);
}
