package project.uberclone.exception.driveRequest;

import org.springframework.http.HttpStatus;


public class DriveRequestNotFoundException extends DriveRequestException{

    private static final String MESSAGE = "Drive request in not found.";
    public DriveRequestNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
