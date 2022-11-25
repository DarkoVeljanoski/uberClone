package project.uberclone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver{

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private Integer age;
    private Double pricePerKilometer;
    private Double averageRating;
    private DriverStatusEnum driverStatus;
    private Integer timesRated;
    private Double latitude;
    private Double longitude;
    private Double distanceToPassenger;

    @ManyToOne
    @JoinColumn(name = "drive_request_id")
    private DriveRequestEntity driveRequestEntity;
}
