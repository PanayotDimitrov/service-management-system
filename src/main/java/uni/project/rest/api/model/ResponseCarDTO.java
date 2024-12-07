package uni.project.rest.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class ResponseCarDTO {

    private int id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Long> garageIds;

}
