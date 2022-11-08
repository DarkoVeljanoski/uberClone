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
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.utils.DriverEntityUtil;
import project.uberclone.utils.EditDriverDetailsRequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {
    @Spy
    @InjectMocks
    private DriverServiceImpl driverService;
    @Mock
    DriverRepository driverRepository;
    @Spy
    ModelMapper modelMapper;

    @Test
    void findDriverByMail(){
        Driver driver = DriverEntityUtil.getDriver();
        when(driverRepository.findByEmail(anyString())).thenReturn((driver));
        Driver actualDriver = driverService.findDriverByEmail(driver.getEmail());
        assertEquals(driver, actualDriver);
    }

    @Test
    void getAllDrivers(){
        List<Driver> driversList = new ArrayList<>();
        driversList.add(DriverEntityUtil.getDriver());
        when(driverRepository.findAll()).thenReturn(driversList);
        List<Driver> expectedResponse = new ArrayList<>(driversList);
        List<DriverResponse> actualResponse = driverService.getAllDrivers();
        Assertions.assertEquals(expectedResponse.size(), actualResponse.size());
        Assertions.assertEquals(expectedResponse.get(0).getEmail(), actualResponse.get(0).getEmail());
    }

    @Test
    void getDriverById(){
        Driver driver = DriverEntityUtil.getDriver();
        when(driverRepository.findById(any())).thenReturn(Optional.of(driver));

        DriverResponse actualResponse = driverService.getDriverById(driver.getId());

        Assertions.assertEquals(driver.getEmail(), actualResponse.getEmail());
    }

    @Test
    void editDriverById(){
        Driver driver = DriverEntityUtil.getDriver();
        EditDriverDetailsRequest editedDriver = EditDriverDetailsRequestUtil.get();
        when(driverRepository.findById(any())).thenReturn(Optional.of(driver));
        when(driverRepository.save(any())).thenReturn(driver);

        DriverResponse driverResponse = driverService.editDriver(driver.getId(), editedDriver);
        Assertions.assertEquals(driver.getEmail(), driverResponse.getEmail());

    }

    @Test
    void deleteDriver(){
        Driver driver = DriverEntityUtil.getDriver();
        when(driverRepository.findById(any())).thenReturn(Optional.of(driver));

        driverService.deleteDriver(driver.getId());
        verify(driverRepository).deleteById(driver.getId());
    }



}