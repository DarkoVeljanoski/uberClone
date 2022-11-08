package project.uberclone.service;

import project.uberclone.model.entity.Driver;
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.response.DriverResponse;

import java.util.List;

public interface DriverService {
    Driver findDriverByEmail(String username);

    List<DriverResponse> getAllDrivers();

    DriverResponse getDriverById(Long id);

    DriverResponse editDriver(Long id, EditDriverDetailsRequest editDriverRequest);

    void deleteDriver(Long id);
}
