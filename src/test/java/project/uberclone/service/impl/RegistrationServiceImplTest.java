package project.uberclone.service.impl;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.Passenger;
import project.uberclone.model.request.PassengerRegistrationRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.GeoIpResponse;
import project.uberclone.model.response.PassengerResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.repository.PassengerRepository;
import project.uberclone.service.GeoIpService;
import project.uberclone.service.PasswordEncoderService;
import project.uberclone.utils.DriverEntityUtil;
import project.uberclone.utils.PassengerEntityUtil;
import project.uberclone.utils.PassengerRegistrationRequestUtil;
import project.uberclone.utils.RegisterDriverRequestUtil;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceImplTest {
    @Spy
    @InjectMocks
    private RegistrationServiceImpl registrationService;
    @Mock
    DriverRepository driverRepository;

    @Mock
    PassengerRepository passengerRepository;

    @Mock
    GeoIpServiceImpl geoIpService;
    @Mock
    PasswordEncoderService passwordEncoderService;
    @Spy
    ModelMapper modelMapper;

    @Test
    void registerDriver() throws IOException, GeoIp2Exception {
        Driver expectedDriver = DriverEntityUtil.getDriver();
        when(driverRepository.save(any())).thenReturn(expectedDriver);
        when(passwordEncoderService.encode(anyString())).thenReturn("encryptedPassword");
        GeoIpResponse geoIpResponse = new GeoIpResponse();
        when(geoIpService.getIpLocation(anyString())).thenReturn(geoIpResponse);

        RegisterDriverRequest driverRequest = RegisterDriverRequestUtil.get();
        MockHttpServletRequest request = new MockHttpServletRequest();
        DriverResponse actualResponse = registrationService.registerDriver(driverRequest, request);

        Assertions.assertEquals(expectedDriver.getEmail(),actualResponse.getEmail());
    }

    @Test
    void registerPassenger(){
        Passenger expectedPassenger = PassengerEntityUtil.getPassenger();
        when(passengerRepository.save(any())).thenReturn(expectedPassenger);
        when(passwordEncoderService.encode(anyString())).thenReturn("encryptedPassword");

        PassengerRegistrationRequest passengerRegistrationRequest = PassengerRegistrationRequestUtil.get();
        PassengerResponse actualResponse = registrationService.registerPassenger(passengerRegistrationRequest);

        Assertions.assertEquals(expectedPassenger.getEmail(),actualResponse.getEmail());
    }

}