package project.uberclone.service;

import project.uberclone.model.request.EditPassengerDetailsRequest;
import project.uberclone.model.response.PassengerResponse;

import java.util.List;

public interface PassengerService {
    List<PassengerResponse> getAllPassengers();

    PassengerResponse getPassengerById(Long id);

    PassengerResponse editPassenger(Long id, EditPassengerDetailsRequest editPassengerDetailsRequest);

    void deletePassenger(Long id);


}
