package project.uberclone.utils;

import project.uberclone.model.entity.Passenger;

public class PassengerEntityUtil {

    public static Passenger getPassenger(){
        return new Passenger(2L, "passenger@mail.com","password",
                "passengerName","passengerLastname",33);
    }
}
