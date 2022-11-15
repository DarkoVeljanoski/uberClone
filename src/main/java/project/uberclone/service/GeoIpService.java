package project.uberclone.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import project.uberclone.model.response.GeoIpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GeoIpService {

    GeoIpResponse getIpLocation(String ip) throws IOException, GeoIp2Exception;

    String getLocalIp(HttpServletRequest request);

    String getClientIp(HttpServletRequest request);
}
