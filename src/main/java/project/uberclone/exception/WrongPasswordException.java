package project.uberclone.exception;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends BaseException{

    private final static String MESSAGE = "provided password is wrong.";
    public WrongPasswordException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
