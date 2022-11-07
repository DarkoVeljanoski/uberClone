package project.uberclone.exception.jwt;

import org.springframework.http.HttpStatus;

public class InvalidSessionTokenException extends JwtException{

    private final static String MESSAGE = "Session token is invalid.";
    public InvalidSessionTokenException() {
        super(MESSAGE, HttpStatus.UNAUTHORIZED);
    }
}
