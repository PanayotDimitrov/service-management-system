package uni.project.rest.api.model;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class CreateCarDTO {

    private String make;
    private String model;
    private int year;
    private String licensePlate;
    private List<Long> garageIds;

}
