package project.uberclone.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "driver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Driver {

    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private Integer age;
    private Double pricePerKilometer;
    private Double averageRating;
    private DriverStatusEnum driverStatus;
}
