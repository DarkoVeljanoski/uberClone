package project.uberclone.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.DoubleBuffer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RateDriverRequest {
    private Double rating;
}
