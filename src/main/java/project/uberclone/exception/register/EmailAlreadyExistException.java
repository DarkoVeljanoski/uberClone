package project.uberclone.exception.register;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyExistException extends RegisterException{

    private static final String MESSAGE = "Email already exist";
    public EmailAlreadyExistException() {
        super(MESSAGE, HttpStatus.CONFLICT);
    }
}
