package uni.project.rest.api.model;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CreateMaintenanceDTO {
    private long garageId;
    private long carId;
    private String serviceType;
    private LocalDate scheduledDate;

}
