package project.uberclone.service;

import project.uberclone.model.entity.Driver;

public interface DriverService {
    Driver findDriverByEmail(String username);
}
