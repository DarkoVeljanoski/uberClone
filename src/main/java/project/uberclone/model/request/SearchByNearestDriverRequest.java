package project.uberclone.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchByNearestDriverRequest {

    private Double longitude;
    private Double latitude;
}
