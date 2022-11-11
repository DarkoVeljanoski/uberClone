package project.uberclone.exception.passenger;

import org.springframework.http.HttpStatus;

public class PassengerNotFoundException extends PassengerException{

    private static String MESSAGE = "Passenger is not found.";
    public PassengerNotFoundException() {
        super(MESSAGE, HttpStatus.NOT_FOUND);
    }
}
