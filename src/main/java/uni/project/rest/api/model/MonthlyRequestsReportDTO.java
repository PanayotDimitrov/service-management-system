package uni.project.rest.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
public class MonthlyRequestsReportDTO {

    private String yearMonth; // Format: YYYY-MM
    private int requests;

}
