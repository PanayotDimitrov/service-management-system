package uni.project.rest.api.model;
import lombok.*;

import java.time.LocalDate;
@Getter
@Data
@Setter
@NoArgsConstructor
public class ResponseMaintenanceDTO {

    private Long id;
    private Long carId;
    private String carName;
    private String serviceType;
    private LocalDate scheduledDate;
    private Long garageId;
    private String garageName;

}
