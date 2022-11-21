package project.uberclone.service.impl;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.uberclone.exception.register.EmailAlreadyExistException;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.entity.DriverStatusEnum;
import project.uberclone.model.entity.Passenger;
import project.uberclone.model.request.PassengerRegistrationRequest;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.model.response.GeoIpResponse;
import project.uberclone.model.response.PassengerResponse;
import project.uberclone.repository.DriverRepository;
import project.uberclone.repository.PassengerRepository;
import project.uberclone.service.GeoIpService;
import project.uberclone.service.PasswordEncoderService;
import project.uberclone.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final DriverRepository driverRepository;
    private final PassengerRepository passengerRepository;
    private final PasswordEncoderService passwordEncoderService;
    private final ModelMapper modelMapper;

    private final GeoIpService geoIpService;

    @Override
    public DriverResponse registerDriver(RegisterDriverRequest registerDriverRequest, HttpServletRequest request) throws IOException, GeoIp2Exception {
        if(driverRepository.existsByEmail(registerDriverRequest.getEmail())){
            throw new EmailAlreadyExistException();
        }
        Driver driverEntity = modelMapper.map(registerDriverRequest, Driver.class);
        driverEntity.setPassword(passwordEncoderService.encode(registerDriverRequest.getPassword()));
        setDriverEssentials(driverEntity);
        setDriverLocation(driverEntity,request);
        driverRepository.save(driverEntity);
        return modelMapper.map(driverEntity, DriverResponse.class);
    }

    private void setDriverEssentials(Driver driverEntity){
        driverEntity.setDriverStatus(DriverStatusEnum.AVAILABLE);
        driverEntity.setAverageRating(0.0);
        driverEntity.setTimesRated(0);
    }

    public void setDriverLocation(Driver driverEntity,HttpServletRequest request) throws IOException, GeoIp2Exception {
       // String ip = geoIpService.getClientIp(request);
        String manualIp = "89.205.124.122";
        GeoIpResponse geoIpResponse = geoIpService.getIpLocation(manualIp);
        Double latitude = geoIpResponse.getLatitude();
        Double longitude = geoIpResponse.getLongitude();
        driverEntity.setLatitude(latitude);
        driverEntity.setLongitude(longitude);
    }

    @Override
    public PassengerResponse registerPassenger(PassengerRegistrationRequest passengerRegistrationRequest) {
        if(passengerRepository.existsByEmail(passengerRegistrationRequest.getEmail())){
            throw new EmailAlreadyExistException();
        }
        Passenger passenger = modelMapper.map(passengerRegistrationRequest, Passenger.class);
        passenger.setPassword(passwordEncoderService.encode(passengerRegistrationRequest.getPassword()));
        passengerRepository.save(passenger);
        return modelMapper.map(passenger, PassengerResponse.class);
    }
}
