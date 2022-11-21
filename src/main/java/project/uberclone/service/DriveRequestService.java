package project.uberclone.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import project.uberclone.model.request.DriveRequestRequest;
import project.uberclone.model.response.DriveRequestResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface DriveRequestService {
    DriveRequestResponse sentDriverRequestToDriver(Long id, DriveRequestRequest driveRequestRequest);

    DriveRequestResponse getDriverRequest(Long driverId);

    void deleteDriveRequest(Long id, HttpServletRequest request) throws IOException, GeoIp2Exception;
}
