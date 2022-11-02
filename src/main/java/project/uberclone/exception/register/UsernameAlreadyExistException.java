package project.uberclone.exception.register;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistException extends RegisterException{

    private static final String MESSAGE = "Username already exist";
    public UsernameAlreadyExistException() {
        super(MESSAGE, HttpStatus.CONFLICT);
    }
}
