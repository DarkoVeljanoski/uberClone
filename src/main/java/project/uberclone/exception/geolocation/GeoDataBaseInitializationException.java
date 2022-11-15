package project.uberclone.exception.geolocation;

import org.springframework.http.HttpStatus;

public class GeoDataBaseInitializationException extends GeoData {

    private static String MESSAGE = "Geolite2 database couldn't be initialized.";
    public GeoDataBaseInitializationException() {
        super(MESSAGE, HttpStatus.EXPECTATION_FAILED);
    }
}
