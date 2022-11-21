package project.uberclone.service.impl;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.mock.web.MockHttpServletRequest;
import project.uberclone.model.entity.DriveRequestEntity;
import project.uberclone.model.request.DriveRequestRequest;
import project.uberclone.model.response.DriveRequestResponse;
import project.uberclone.repository.DriveRequestRepository;
import project.uberclone.utils.DriveRequestEntityUtil;
import project.uberclone.utils.DriveRequestRequestUtil;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DriveRequestServiceImplTest {
    @Spy
    @InjectMocks
    private DriveRequestServiceImpl driveRequestService;

    @Mock private DriverServiceImpl driverService;

    @Mock private DriveRequestRepository driveRequestRepository;

    @Spy
    ModelMapper modelMapper;

    @Test
    void sendDriveRequestToDriver(){
        DriveRequestEntity expectedDriveRequestEntity = DriveRequestEntityUtil.get();
        when(driveRequestRepository.save(any())).thenReturn(expectedDriveRequestEntity);

        DriveRequestRequest driveRequestRequest = DriveRequestRequestUtil.get();
        Long driverId = expectedDriveRequestEntity.getDriverId();
        DriveRequestResponse actualResponse = driveRequestService.sentDriverRequestToDriver(driverId,driveRequestRequest);

        Assertions.assertEquals(expectedDriveRequestEntity.getDestinationLongitude(), actualResponse.getDestinationLongitude());
        Assertions.assertEquals(expectedDriveRequestEntity.getDestinationLatitude(), actualResponse.getDestinationLatitude());
        Assertions.assertEquals(expectedDriveRequestEntity.getPickupLatitude(),actualResponse.getPickupLatitude());
        Assertions.assertEquals(expectedDriveRequestEntity.getPickupLongitude(), actualResponse.getPickupLongitude());
    }

    @Test
    void getDriveRequest(){
        DriveRequestEntity expectedDriveRequestEntity = DriveRequestEntityUtil.get();
        Long driverId = expectedDriveRequestEntity.getDriverId();
        when(driveRequestRepository.findByDriverId(driverId)).thenReturn(expectedDriveRequestEntity);

        DriveRequestResponse actualResponse = driveRequestService.getDriverRequest(driverId);

        Assertions.assertEquals(expectedDriveRequestEntity.getPickupLongitude(),actualResponse.getPickupLongitude());
        Assertions.assertEquals(expectedDriveRequestEntity.getDestinationLatitude(), actualResponse.getDestinationLatitude());
    }

    @Test
    void deleteDriveRequest() throws IOException, GeoIp2Exception {
        DriveRequestEntity expectedDriveRequestEntity = DriveRequestEntityUtil.get();
        when(driveRequestRepository.findById(any())).thenReturn(Optional.of(expectedDriveRequestEntity));

        MockHttpServletRequest request = new MockHttpServletRequest();
        driveRequestService.deleteDriveRequest(expectedDriveRequestEntity.getId(),request);
        verify(driveRequestRepository).deleteById(expectedDriveRequestEntity.getId());
    }







}