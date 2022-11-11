package project.uberclone.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerResponse {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private Integer age;
}
