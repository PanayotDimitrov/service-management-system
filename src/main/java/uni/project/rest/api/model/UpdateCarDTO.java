package uni.project.rest.api.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UpdateCarDTO {

    private long id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Long> garages;

}
