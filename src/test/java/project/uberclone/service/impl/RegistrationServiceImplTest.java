package project.uberclone.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.Passenger;
import project.uberclone.model.request.PassengerRegistrationRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.PassengerResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.repository.PassengerRepository;
import project.uberclone.service.PasswordEncoderService;
import project.uberclone.utils.DriverEntityUtil;
import project.uberclone.utils.PassengerEntityUtil;
import project.uberclone.utils.PassengerRegistrationRequestUtil;
import project.uberclone.utils.RegisterDriverRequestUtil;

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
    PasswordEncoderService passwordEncoderService;
    @Spy
    ModelMapper modelMapper;

    @Test
    void registerDriver(){
        Driver expectedDriver = DriverEntityUtil.getDriver();
        when(driverRepository.save(any())).thenReturn(expectedDriver);
        when(passwordEncoderService.encode(anyString())).thenReturn("encryptedPassword");

        RegisterDriverRequest driverRequest = RegisterDriverRequestUtil.get();
        DriverResponse actualResponse = registrationService.registerDriver(driverRequest);

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