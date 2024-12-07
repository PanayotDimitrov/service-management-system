package uni.project.rest.api.model;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Data
public class ResponseMaintenanceDTO {

    private long id;
    private long carId;
    private String carName;
    private String serviceType;
    private LocalDate scheduledDate;
    private Long garageId;
    private String garageName;

}
