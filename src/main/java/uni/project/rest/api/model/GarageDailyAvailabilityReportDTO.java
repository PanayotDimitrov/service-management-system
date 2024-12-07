package uni.project.rest.api.model;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Data
@Setter
public class GarageDailyAvailabilityReportDTO {

    private LocalDate date;
    private int requests;
    private int availableCapacity;

}
