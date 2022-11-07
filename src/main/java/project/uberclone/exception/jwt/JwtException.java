package project.uberclone.exception.jwt;

import org.springframework.http.HttpStatus;
import project.uberclone.exception.BaseException;

public class JwtException extends BaseException {
    public JwtException(String message, HttpStatus status) {
        super(message, status);
    }
}
