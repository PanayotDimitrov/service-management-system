package uni.project.rest.api.model;

import lombok.*;

import java.util.List;

@Getter
@Data
@NoArgsConstructor
@Setter
public class ResponseCarDTO {

    private Long id;
    private String make;
    private String model;
    private int productionYear;
    private String licensePlate;
    private List<Long> garageIds;

}
