package project.uberclone.exception.passenger;

import org.springframework.http.HttpStatus;
import project.uberclone.exception.BaseException;

public class PassengerException extends BaseException {
    public PassengerException(String message, HttpStatus status) {
        super(message, status);
    }
}
