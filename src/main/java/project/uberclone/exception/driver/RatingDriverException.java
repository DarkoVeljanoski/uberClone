package project.uberclone.exception.driver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RatingDriverException extends DriverException{

    private static String MESSAGE = "Please enter rating from 0 - 5.";
    public RatingDriverException() {
        super(MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
