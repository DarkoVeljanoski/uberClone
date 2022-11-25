package project.uberclone.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.DriverStatusEnum;
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.request.RateDriverRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.request.SearchByNearestDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.service.DriverService;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {

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

    @GetMapping("/{id}/status")
    public DriverStatusEnum getStatusById(@PathVariable Long id){
        return driverService.getStatusById(id);
    }

    @PutMapping("/{id}/rate")
    public Double rateDriver(@PathVariable Long id, @RequestBody RateDriverRequest rateDriverRequest){
        return driverService.rateDriver(id, rateDriverRequest);
    }

    @PutMapping("/searchByNearest")
    public List<DriverResponse> searchByNearest(@RequestBody SearchByNearestDriverRequest searchByNearestDriverRequest){
        return driverService.searchByNearestDriver(searchByNearestDriverRequest);
    }

    @GetMapping("/sortByPrice")
    public List<DriverResponse> sortByPricePerKm(){
        return driverService.sortByPricePerKm();
    }

    @GetMapping("/sortByRating")
    public List<DriverResponse> sortByRating(){
        return driverService.sortByRating();
    }





}
