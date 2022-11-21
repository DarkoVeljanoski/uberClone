package project.uberclone.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.DriverStatusEnum;
import project.uberclone.model.request.EditDriverDetailsRequest;
import project.uberclone.model.request.RateDriverRequest;
import project.uberclone.model.response.DriverResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface DriverService {
    Driver findDriverByEmail(String username);

    List<DriverResponse> getAllDrivers();

    DriverResponse getDriverById(Long id);

    DriverResponse editDriver(Long id, EditDriverDetailsRequest editDriverRequest);

    void deleteDriver(Long id);

    DriverStatusEnum getStatusById(Long id);

    Driver checkIfExistAndReturnById(Long id);

    Boolean checkIfBusy(Long id);

    void changeStatusToBusy(Driver driver);

    void changeStatusToAvailable(Driver driver, HttpServletRequest request) throws IOException, GeoIp2Exception;

    Double rateDriver(Long id, RateDriverRequest rateDriverRequest);
}
