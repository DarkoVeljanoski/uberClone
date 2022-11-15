package project.uberclone.controller;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import project.uberclone.model.response.GeoIpResponse;
import project.uberclone.service.GeoIpService;
import project.uberclone.util.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class GeoIpController {

    private final GeoIpService geoIpService;

    @PostMapping("/geoIp/{ipAddress}")
    public GeoIpResponse getLocationByIpAddress(@PathVariable String ipAddress) throws IOException, GeoIp2Exception {
        return geoIpService.getIpLocation(ipAddress);
    }

    @GetMapping("/clientIp")
    public String getClientIPAddress(HttpServletRequest request) {
       return geoIpService.getClientIp(request);
    }

    @GetMapping("/localIp")
    public String getLocalIp(HttpServletRequest request){
        return geoIpService.getLocalIp(request);
    }


}
