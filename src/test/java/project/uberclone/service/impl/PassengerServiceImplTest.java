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
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.request.EditPassengerDetailsRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.PassengerResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.repository.PassengerRepository;
import project.uberclone.utils.DriverEntityUtil;
import project.uberclone.utils.EditDriverDetailsRequestUtil;
import project.uberclone.utils.EditPassengerDetailsRequestUtil;
import project.uberclone.utils.PassengerEntityUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PassengerServiceImplTest {

    @Spy
    @InjectMocks
    private PassengerServiceImpl passengerService;
    @Mock
    PassengerRepository passengerRepository;
    @Spy
    ModelMapper modelMapper;

    @Test
    void getAllPassengers(){
        List<Passenger> passengerList = new ArrayList<>();
        passengerList.add(PassengerEntityUtil.getPassenger());
        when(passengerRepository.findAll()).thenReturn(passengerList);
        List<Passenger> expectedResponse = new ArrayList<>(passengerList);
        List<PassengerResponse> actualResponse = passengerService.getAllPassengers();
        Assertions.assertEquals(expectedResponse.size(), actualResponse.size());
        Assertions.assertEquals(expectedResponse.get(0).getEmail(), actualResponse.get(0).getEmail());
    }

    @Test
    void getPassengerById(){
        Passenger passenger = PassengerEntityUtil.getPassenger();
        when(passengerRepository.findById(any())).thenReturn(Optional.of(passenger));

        PassengerResponse actualResponse = passengerService.getPassengerById(passenger.getId());

        Assertions.assertEquals(passenger.getEmail(), actualResponse.getEmail());
    }

    @Test
    void editPassengerById(){
        Passenger passenger = PassengerEntityUtil.getPassenger();
        EditPassengerDetailsRequest editedPassenger = EditPassengerDetailsRequestUtil.get();
        when(passengerRepository.findById(any())).thenReturn(Optional.of(passenger));
        when(passengerRepository.save(any())).thenReturn(passenger);

        PassengerResponse passengerResponse = passengerService.editPassenger(passenger.getId(), editedPassenger);
        Assertions.assertEquals(passenger.getEmail(), passengerResponse.getEmail());

    }

    @Test
    void deleteDriver(){
        Passenger passenger = PassengerEntityUtil.getPassenger();
        when(passengerRepository.findById(any())).thenReturn(Optional.of(passenger));

        passengerService.deletePassenger(passenger.getId());
        verify(passengerRepository).deleteById(passenger.getId());
    }

}