package project.uberclone.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.uberclone.model.entity.DriverStatusEnum;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private Integer age;
    private Double pricePerKilometer;
    private Double averageRating;
    private DriverStatusEnum driverStatus;
}
