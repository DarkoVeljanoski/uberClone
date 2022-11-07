package project.uberclone.exception.jwt;

import org.springframework.http.HttpStatus;

public class InvalidAlgorithmException extends JwtException{

    private final static String MESSAGE = "Invalid Algorithm.";
    public InvalidAlgorithmException() {
        super(MESSAGE, HttpStatus.UNAUTHORIZED);
    }
}
