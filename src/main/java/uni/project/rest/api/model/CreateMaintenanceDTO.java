package uni.project.rest.api.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CreateMaintenanceDTO {
    private long garageId;
    private long carId;
    private String serviceType;
    private LocalDate scheduledDate;

}
