package uni.project.rest.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Data
@Setter
public class UpdateCarDTO {

    private Long id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Long> garages;

}
