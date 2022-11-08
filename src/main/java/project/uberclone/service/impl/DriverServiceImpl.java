package project.uberclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.uberclone.exception.driver.DriverNotFoundException;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.request.EditDriverDetailsRequest;
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
        driverRepository.deleteById(id);
    }

    private Driver checkIfExistAndReturnById(Long id){
        return driverRepository.findById(id).orElseThrow(DriverNotFoundException::new);
    }

    private DriverResponse mapToResponseClass(Driver driver){
        return modelMapper.map(driver, DriverResponse.class);
    }
}
