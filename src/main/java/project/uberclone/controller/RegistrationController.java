package project.uberclone.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.uberclone.model.request.PassengerRegistrationRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.PassengerResponse;
import project.uberclone.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/driver")
    public ResponseEntity<DriverResponse> registerDriver(@RequestBody RegisterDriverRequest registerDriverRequest, HttpServletRequest request) throws IOException, GeoIp2Exception {
        ResponseEntity<DriverResponse> responseEntity =
                new ResponseEntity<>(registrationService.registerDriver(registerDriverRequest, request), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/passenger")
    public ResponseEntity<PassengerResponse> registerPassenger(@RequestBody PassengerRegistrationRequest passengerRegistrationRequest){
        ResponseEntity<PassengerResponse> responseEntity =
                new ResponseEntity<>(registrationService.registerPassenger(passengerRegistrationRequest), HttpStatus.OK);
        return responseEntity;
    }
}
