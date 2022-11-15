package project.uberclone.model.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GeoIpResponse {
    private String ipAddress;
    private String city;
    private String fullLocation;
    private Double latitude;
    private Double longitude;
}
