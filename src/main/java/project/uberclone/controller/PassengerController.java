package project.uberclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.request.EditPassengerDetailsRequest;
import project.uberclone.model.request.SearchByNearestDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.PassengerResponse;
import project.uberclone.service.PassengerService;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping
    public ResponseEntity<List<PassengerResponse>> getAllPassengers(){
        ResponseEntity<List<PassengerResponse>> passengerResponseEntity =
                new ResponseEntity<>(passengerService.getAllPassengers(), HttpStatus.OK);
        return passengerResponseEntity;
    }

    @GetMapping("/{id}")
    public PassengerResponse getPassengerDetails(@PathVariable Long id){
        PassengerResponse response = passengerService.getPassengerById(id);
        return response;
    }

    @PutMapping("/{id}")
    public PassengerResponse editPassenger(@PathVariable Long id, @RequestBody EditPassengerDetailsRequest editPassengerDetailsRequest){
        PassengerResponse passengerResponse = passengerService.editPassenger(id, editPassengerDetailsRequest);
        return passengerResponse;
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id){
        passengerService.deletePassenger(id);
    }
}
