package uni.project.rest.api.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Data
@Setter
public class UpdateMaintenanceDTO {

    private Long carId;
    private String serviceType;
    private LocalDate scheduledDate;
    private long garageId;
}
