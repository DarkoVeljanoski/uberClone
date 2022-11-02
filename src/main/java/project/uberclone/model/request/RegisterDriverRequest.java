package project.uberclone.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDriverRequest {

    @NotBlank(message = "cannot be null or empty")
    private String email;
    @NotBlank(message = "cannot be null or empty")
    private String username;
    @NotBlank(message = "cannot be null or empty")
    private String password;
    @NotBlank(message = "cannot be null or empty")
    private String name;
    @NotBlank(message = "cannot be null or empty")
    private String lastname;
    private Integer age;
    private Double pricePerKilometer;
}
