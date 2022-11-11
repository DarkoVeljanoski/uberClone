package project.uberclone.service;

import project.uberclone.model.request.PassengerRegistrationRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.PassengerResponse;

public interface RegistrationService {
    DriverResponse registerDriver(RegisterDriverRequest registerDriverRequest);

    PassengerResponse registerPassenger(PassengerRegistrationRequest passengerRegistrationRequest);
}
