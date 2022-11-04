package project.uberclone.exception.register;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistException extends RegisterException{

    private static final String MESSAGE = "Email already exist";
    public EmailAlreadyExistException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
