package project.uberclone.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditPassengerDetailsRequest {

    @NotBlank(message = "cannot be null or empty")
    private String name;
    @NotBlank(message = "cannot be null or empty")
    private String lastname;
    private Integer age;
}
