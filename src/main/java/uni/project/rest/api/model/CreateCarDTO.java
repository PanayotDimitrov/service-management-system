package uni.project.rest.api.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CreateCarDTO {

    private String make;
    private String model;
    private Integer productionYear;
    private String licensePlate;
    private List<Long> garageIds;

}
