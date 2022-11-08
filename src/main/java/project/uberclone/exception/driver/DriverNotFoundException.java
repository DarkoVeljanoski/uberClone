package project.uberclone.exception.driver;

import org.springframework.http.HttpStatus;

public class DriverNotFoundException extends DriverException{

    private final static String MESSAGE = "the driver is not found.";
    public DriverNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
