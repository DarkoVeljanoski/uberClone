package project.uberclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.uberclone.exception.driver.DriverNotFoundException;
import project.uberclone.exception.driver.RatingDriverException;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.DriverStatusEnum;
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.request.RateDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.service.DriverService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final ModelMapper modelMapper;
    @Override
    public Driver findDriverByEmail(String username) {
        return driverRepository.findByEmail(username);
    }

    @Override
    public List<DriverResponse> getAllDrivers() {
        List<Driver> drivers = driverRepository.findAll();
        return drivers.stream().map(e -> modelMapper.map(e, DriverResponse.class)).collect(Collectors.toList());
    }

    @Override
    public DriverResponse getDriverById(Long id) {
        Driver driver = checkIfExistAndReturnById(id);
        return mapToResponseClass(driver);
    }

    @Override
    public DriverResponse editDriver(Long id, EditDriverDetailsRequest editDriverRequest) {
        Driver driver = checkIfExistAndReturnById(id);
        modelMapper.map(editDriverRequest, driver);
        driverRepository.save(driver);
        return mapToResponseClass(driver);
    }

    @Override
    public void deleteDriver(Long id) {
        checkIfExistAndReturnById(id);
        driverRepository.deleteById(id);
    }

    @Override
    public DriverStatusEnum getStatusById(Long id) {
        Driver driver = checkIfExistAndReturnById(id);
        return driver.getDriverStatus();
    }

    public Driver checkIfExistAndReturnById(Long id){
        return driverRepository.findById(id).orElseThrow(DriverNotFoundException::new);
    }

    @Override
    public Boolean checkIfBusy(Long id) {
        Driver driver = checkIfExistAndReturnById(id);
        if (driver.getDriverStatus().equals(DriverStatusEnum.BUSY))
            return true;
        else return false;
    }

    @Override
    public void changeStatusToBusy(Driver driver) {
        driver.setDriverStatus(DriverStatusEnum.BUSY);
        driverRepository.save(driver);
    }

    @Override
    public void changeStatusToAvailable(Driver driver) {
        driver.setDriverStatus(DriverStatusEnum.AVAILABLE);
        driverRepository.save(driver);
    }

    @Override
    public Double rateDriver(Long id, RateDriverRequest rateDriverRequest) {
        if (rateDriverRequest.getRating() > 5.0 || rateDriverRequest.getRating() < 0.0){
            throw new RatingDriverException();
        }
        Driver driver = checkIfExistAndReturnById(id);
        driver.setTimesRated(driver.getTimesRated()+1);
        Double newAvg = (driver.getAverageRating() + rateDriverRequest.getRating())/driver.getTimesRated();
        driver.setAverageRating(newAvg);
        driverRepository.save(driver);
        return newAvg;
    }

    private DriverResponse mapToResponseClass(Driver driver){
        return modelMapper.map(driver, DriverResponse.class);
    }
}
