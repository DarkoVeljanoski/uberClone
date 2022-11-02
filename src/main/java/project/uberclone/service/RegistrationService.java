package project.uberclone.service;

import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;

public interface RegistrationService {
    DriverResponse registerDriver(RegisterDriverRequest registerDriverRequest);
}
