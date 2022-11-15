package project.uberclone.exception.geolocation;

import org.springframework.http.HttpStatus;
import project.uberclone.exception.BaseException;

public class GeoData extends BaseException {
    public GeoData(String message, HttpStatus status) {
        super(message, status);
    }
}
