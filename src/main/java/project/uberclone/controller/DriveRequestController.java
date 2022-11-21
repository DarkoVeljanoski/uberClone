package project.uberclone.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.uberclone.model.request.DriveRequestRequest;
import project.uberclone.model.response.DriveRequestResponse;
import project.uberclone.service.DriveRequestService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class DriveRequestController {

    private final DriveRequestService driveRequestService;

    // the id sent in the path is the driver id
    @PostMapping("/driveRequest/{id}")
    public ResponseEntity<DriveRequestResponse> sentDriveRequestToDriver(@PathVariable Long id, @RequestBody DriveRequestRequest driveRequestRequest){
        ResponseEntity<DriveRequestResponse> responseEntity =
                new ResponseEntity<>(driveRequestService.sentDriverRequestToDriver(id, driveRequestRequest),HttpStatus.OK);
        return responseEntity;
    }

    // the id sent in the path is the driver id
    @GetMapping("/driveRequest/{id}")
    public DriveRequestResponse getDriverRequest(@PathVariable Long id){
        return driveRequestService.getDriverRequest(id);
    }

    // the id sent in the path is the drive request id
    @DeleteMapping("driveRequest/{id}")
    public void deleteDriveRequest(@PathVariable Long id, HttpServletRequest request) throws IOException, GeoIp2Exception {
        driveRequestService.deleteDriveRequest(id, request);
    }


}
