package project.uberclone.exception.register;

import org.springframework.http.HttpStatus;
import project.uberclone.exception.BaseException;

public class RegisterException extends BaseException {
    public RegisterException(String message, HttpStatus status) {
        super(message, status);
    }
}
