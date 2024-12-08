package uni.project.rest.api.model;


import lombok.*;

import java.time.LocalDate;

@Getter
@Data
@Setter
@NoArgsConstructor
public class GarageDailyAvailabilityReportDTO {

    private LocalDate date;
    private int requests;
    private int availableCapacity;

    public GarageDailyAvailabilityReportDTO(LocalDate date, int requests, int availableCapacity) {
        this.date = date;
        this.requests = requests;
        this.availableCapacity = availableCapacity;
    }
}
