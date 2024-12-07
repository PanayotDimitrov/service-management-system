package uni.project.rest.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyRequestsReportDTO {

    private String yearMonth; // Format: YYYY-MM
    private int requests;

}
