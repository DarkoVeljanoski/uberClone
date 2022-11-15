package project.uberclone.service.impl;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.uberclone.model.response.GeoIpResponse;
import project.uberclone.service.GeoIpService;
import project.uberclone.util.HttpUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.InetAddress;

import static java.util.Objects.nonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeoIpServiceImpl implements GeoIpService {

    private final DatabaseReader databaseReader;

    @Override
    public GeoIpResponse getIpLocation(String ip) throws IOException, GeoIp2Exception {
        GeoIpResponse position = new GeoIpResponse();
        String location;

        InetAddress ipAddress = InetAddress.getByName(ip);

        CityResponse cityResponse = databaseReader.city(ipAddress);
        if(nonNull(cityResponse) && nonNull(cityResponse.getCity())){
            String continent = (cityResponse.getContinent() != null) ? cityResponse.getContinent().getName() : "";
            String country = (cityResponse.getCountry() != null) ? cityResponse.getCountry().getName() : "";
            location = String.format("%s, %s, %s", continent, country, cityResponse.getCity().getName());
            position.setCity(cityResponse.getCity().getName());
            position.setFullLocation(location);
            position.setLatitude((cityResponse.getLocation()!= null) ? cityResponse.getLocation().getLatitude() : 0);
            position.setLongitude((cityResponse.getLocation()!= null) ? cityResponse.getLocation().getLongitude() : 0);
            position.setIpAddress(ip);
        }
        return position;
    }

    @Override
    public String getLocalIp(HttpServletRequest request) {
        return request.getLocalAddr();
    }

    @Override
    public String getClientIp(HttpServletRequest request) {
        return HttpUtils.getRequestIP(request);
    }
}
