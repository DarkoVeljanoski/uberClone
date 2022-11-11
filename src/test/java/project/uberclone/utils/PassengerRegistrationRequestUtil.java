package project.uberclone.utils;

import project.uberclone.model.request.PassengerRegistrationRequest;

public class PassengerRegistrationRequestUtil {

    public static PassengerRegistrationRequest get(){
        return new PassengerRegistrationRequest("passenger@mail.com","password",
                "passengerName","passengerLastname",33);
    }
}
