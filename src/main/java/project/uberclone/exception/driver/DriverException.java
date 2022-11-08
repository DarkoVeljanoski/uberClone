package project.uberclone.exception.driver;

import org.springframework.http.HttpStatus;
import project.uberclone.exception.BaseException;

public class DriverException extends BaseException {
    public DriverException(String message, HttpStatus status) {
        super(message, status);
    }
}
