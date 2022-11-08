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
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.service.PasswordEncoderService;
import project.uberclone.utils.DriverEntityUtil;
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

}