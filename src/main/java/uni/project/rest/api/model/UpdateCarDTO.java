package uni.project.rest.api.model;

import lombok.*;

import java.util.List;
@Getter
@Data
@Setter
@NoArgsConstructor
public class UpdateCarDTO {

    private Long id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Long> garages;

}
