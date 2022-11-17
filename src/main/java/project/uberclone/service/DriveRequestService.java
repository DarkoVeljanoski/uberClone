package project.uberclone.service;

import project.uberclone.model.request.DriveRequestRequest;
import project.uberclone.model.response.DriveRequestResponse;

public interface DriveRequestService {
    DriveRequestResponse sentDriverRequestToDriver(Long id, DriveRequestRequest driveRequestRequest);

    DriveRequestResponse getDriverRequest(Long driverId);

    void deleteDriveRequest(Long id);
}
