package project.uberclone.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.uberclone.exception.register.UsernameAlreadyExistException;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.repository.PassengerRepository;
import project.uberclone.service.RegistrationService;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private DriverRepository driverRepository;
    private PassengerRepository passengerRepository;
    private PasswordEncoder passwordEncoder;
    private ModelMapper modelMapper;

    @Override
    public DriverResponse registerDriver(RegisterDriverRequest registerDriverRequest) {
        if(driverRepository.findByUsername(registerDriverRequest.getUsername())){
            throw new UsernameAlreadyExistException();
        }
        Driver driverEntity = modelMapper.map(registerDriverRequest, Driver.class);
        driverEntity.setPassword(passwordEncoder.encode(registerDriverRequest.getPassword()));
        driverRepository.save(driverEntity);
        return modelMapper.map(driverEntity, DriverResponse.class);
    }
}
