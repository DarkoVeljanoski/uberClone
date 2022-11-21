package project.uberclone.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import project.uberclone.model.request.PassengerRegistrationRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.PassengerResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface RegistrationService {
    DriverResponse registerDriver(RegisterDriverRequest registerDriverRequest, HttpServletRequest request) throws IOException, GeoIp2Exception;

    PassengerResponse registerPassenger(PassengerRegistrationRequest passengerRegistrationRequest);
}
