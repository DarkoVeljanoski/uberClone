package project.uberclone.service;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import project.uberclone.model.response.GeoIpResponse;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface GeoIpService {

    GeoIpResponse getIpLocation(String ip) throws IOException, GeoIp2Exception;

    String getLocalIp(HttpServletRequest request);

    String getClientIp(HttpServletRequest request);

    Double calculateDistanceBetweenLocations(Double lat1,Double lon1,Double lat2, Double lon2, char unit);
}
