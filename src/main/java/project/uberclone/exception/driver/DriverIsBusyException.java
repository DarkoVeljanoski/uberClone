package project.uberclone.exception.driver;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DriverIsBusyException extends DriverException{

    private final static String MESSAGE = "The Driver is busy at the moment.";
    public DriverIsBusyException() {
        super(MESSAGE, HttpStatus.CONFLICT);
    }
}
