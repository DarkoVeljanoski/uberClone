package project.uberclone.exception.driveRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import project.uberclone.exception.BaseException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class DriveRequestException extends BaseException {
    public DriveRequestException(String message, HttpStatus status) {
        super(message, status);
    }
}
