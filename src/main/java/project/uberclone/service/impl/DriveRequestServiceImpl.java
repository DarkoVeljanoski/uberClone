package project.uberclone.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.uberclone.exception.driveRequest.DriveRequestNotFoundException;
import project.uberclone.exception.driver.DriverIsBusyException;
import project.uberclone.model.entity.DriveRequestEntity;
import project.uberclone.model.entity.Driver;
import project.uberclone.model.request.DriveRequestRequest;
import project.uberclone.model.response.DriveRequestResponse;
import project.uberclone.repository.DriveRequestRepository;
import project.uberclone.service.DriveRequestService;
import project.uberclone.service.DriverService;

@Service
@RequiredArgsConstructor
public class DriveRequestServiceImpl implements DriveRequestService {

    private final DriverService driverService;
    private final DriveRequestRepository driveRequestRepository;
    private final ModelMapper modelMapper;

    @Override
    public DriveRequestResponse sentDriverRequestToDriver(Long id, DriveRequestRequest driveRequestRequest) {
        Driver driver = driverService.checkIfExistAndReturnById(id);
        if(driverService.checkIfBusy(id))
            throw new DriverIsBusyException();
        DriveRequestEntity driveRequestEntity = modelMapper.map(driveRequestRequest, DriveRequestEntity.class);
        driveRequestEntity.setDriverId(id);
        driveRequestRepository.save(driveRequestEntity);
        driverService.changeStatusToBusy(driver);
        return modelMapper.map(driveRequestEntity, DriveRequestResponse.class);
    }

    @Override
    public DriveRequestResponse getDriverRequest(Long driverId) {
        driverService.checkIfExistAndReturnById(driverId);
        DriveRequestEntity driveRequestEntity = driveRequestRepository.findByDriverId(driverId);
        return modelMapper.map(driveRequestEntity, DriveRequestResponse.class);
    }

    @Override
    public void deleteDriveRequest(Long id) {
        DriveRequestEntity driveRequestEntity = checkIfRequestExist(id);
        Long driverId = driveRequestEntity.getDriverId();
        Driver driver = driverService.checkIfExistAndReturnById(driverId);
        driverService.changeStatusToAvailable(driver);
        driveRequestRepository.deleteById(id);
    }

    private DriveRequestEntity checkIfRequestExist(Long id){
        return driveRequestRepository.findById(id).orElseThrow(DriveRequestNotFoundException::new);
    }
}
