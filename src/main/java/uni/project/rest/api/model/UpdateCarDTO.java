package uni.project.rest.api.model;

import lombok.*;
import uni.project.rest.api.entity.Garage;

import java.util.List;
@Getter
@Data
@Setter
@NoArgsConstructor
public class UpdateCarDTO {

    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Long> garageIds;

}
