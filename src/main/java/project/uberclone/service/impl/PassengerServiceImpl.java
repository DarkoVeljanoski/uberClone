package project.uberclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.uberclone.exception.driver.DriverNotFoundException;
import project.uberclone.exception.passenger.PassengerNotFoundException;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.Passenger;
import project.uberclone.model.request.EditPassengerDetailsRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.PassengerResponse;
import project.uberclone.repository.PassengerRepository;
import project.uberclone.service.PassengerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<PassengerResponse> getAllPassengers() {
        List<Passenger> passengers = passengerRepository.findAll();
        return passengers.stream().map(e -> modelMapper.map(e, PassengerResponse.class)).collect(Collectors.toList());
    }

    @Override
    public PassengerResponse getPassengerById(Long id) {
        Passenger passenger = checkIfExistAndReturnById(id);
        return mapToResponseClass(passenger);
    }

    @Override
    public PassengerResponse editPassenger(Long id, EditPassengerDetailsRequest editPassengerDetailsRequest) {
        Passenger passenger = checkIfExistAndReturnById(id);
        modelMapper.map(editPassengerDetailsRequest, passenger);
        passengerRepository.save(passenger);
        return mapToResponseClass(passenger);
    }

    @Override
    public void deletePassenger(Long id) {
        checkIfExistAndReturnById(id);
        passengerRepository.deleteById(id);
    }


    private Passenger checkIfExistAndReturnById(Long id){
        return passengerRepository.findById(id).orElseThrow(PassengerNotFoundException::new);
    }

    private PassengerResponse mapToResponseClass(Passenger passenger){
        return modelMapper.map(passenger, PassengerResponse.class);
    }
}
