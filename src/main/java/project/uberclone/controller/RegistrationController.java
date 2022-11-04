package project.uberclone.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.uberclone.model.request.RegisterDriverRequest;
import project.uberclone.model.response.DriverResponse;
import project.uberclone.service.RegistrationService;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/driver")
    public ResponseEntity<DriverResponse> registerDriver(@RequestBody RegisterDriverRequest registerDriverRequest){
        ResponseEntity<DriverResponse> responseEntity =
                new ResponseEntity<>(registrationService.registerDriver(registerDriverRequest), HttpStatus.OK);
        return responseEntity;
    }
}
