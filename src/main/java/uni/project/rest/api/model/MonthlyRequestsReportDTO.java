package uni.project.rest.api.model;

import lombok.*;

import java.time.LocalDate;

@Getter
@Data
@NoArgsConstructor
@Setter
public class MonthlyRequestsReportDTO {

    private LocalDate yearMonth; // Format: YYYY-MM
    private int requests;

}
