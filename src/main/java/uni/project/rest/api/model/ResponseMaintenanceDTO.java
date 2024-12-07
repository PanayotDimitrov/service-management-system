package uni.project.rest.api.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Data
@Setter
public class ResponseMaintenanceDTO {

    private Long id;
    private Long carId;
    private String carName;
    private String serviceType;
    private LocalDate scheduledDate;
    private Long garageId;
    private String garageName;

}
