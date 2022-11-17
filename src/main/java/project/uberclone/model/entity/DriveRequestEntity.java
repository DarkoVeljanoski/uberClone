package project.uberclone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "drive_request")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriveRequestEntity {

    @Id
    @GeneratedValue
    private Long Id;

    private Long driverId;
    private Long passengerId;
    private Double pickupLatitude;
    private Double pickupLongitude;
    private Double destinationLatitude;
    private Double destinationLongitude;


}
