package project.uberclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.service.DriverService;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    @Override
    public Driver findDriverByEmail(String username) {
        Driver driver = driverRepository.findByEmail(username);
        return driver;
    }
}
