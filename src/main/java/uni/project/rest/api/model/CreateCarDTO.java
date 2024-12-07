package uni.project.rest.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Data
public class CreateCarDTO {

    private String make;
    private String model;
    private Integer productionYear;
    private String licensePlate;
    private List<Long> garageIds;

}
