package project.uberclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

    //TODO: get coordinates

    // TODO: handle driving request

    private final DriverService driverService;

    @GetMapping
    public ResponseEntity<List<DriverResponse>> getAllDrivers(){
        ResponseEntity<List<DriverResponse>> driverResponseEntity =
                new ResponseEntity<>(driverService.getAllDrivers(), HttpStatus.OK);
        return driverResponseEntity;
    }

    @GetMapping("/{id}")
    public DriverResponse getDriverDetails(@PathVariable Long id){
        DriverResponse response = driverService.getDriverById(id);
        return response;
    }

    @PutMapping("/{id}")
    public DriverResponse editDriver(@PathVariable Long id, @RequestBody EditDriverDetailsRequest editDriverRequest){
        DriverResponse driverResponse = driverService.editDriver(id, editDriverRequest);
        return driverResponse;
    }

    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable Long id){
        driverService.deleteDriver(id);
    }
}
