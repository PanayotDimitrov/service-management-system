package uni.project.rest.api.model;

import lombok.*;

import java.time.YearMonth;


@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class MonthlyRequestsReportDTO {

    private YearMonthDetail yearMonth;
    private int requests;

    @Getter
    @Setter
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class YearMonthDetail {
        private int year;
        private String month;
        private int monthValue;
        private boolean leapYear;
    }

}
