package project.uberclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.uberclone.exception.register.EmailAlreadyExistException;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.repository.PassengerRepository;
import project.uberclone.service.RegistrationService;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    @Override
    public DriverResponse registerDriver(RegisterDriverRequest registerDriverRequest) {
        if(driverRepository.existsByEmail(registerDriverRequest.getEmail())){
            throw new EmailAlreadyExistException();
        }
        Driver driverEntity = modelMapper.map(registerDriverRequest, Driver.class);
        driverEntity.setPassword(passwordEncoder.encode(registerDriverRequest.getPassword()));
        driverRepository.save(driverEntity);
        return modelMapper.map(driverEntity, DriverResponse.class);
    }
}
