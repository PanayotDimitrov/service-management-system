package uni.project.rest.api.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class UpdateMaintenanceDTO {

    private long carId;
    private String serviceType;
    private LocalDate scheduledDate;
    private long garageId;
}
