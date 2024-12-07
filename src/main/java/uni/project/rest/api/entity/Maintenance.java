package uni.project.rest.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "maintenance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Maintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id")
    private Long id;

    @Column(nullable = false,name = "car_id")
    private Long carId;

    @Column(nullable = false,name = "service_type")
    private String serviceType;

    @Column(nullable = false,name = "scheduled_date")
    private LocalDate scheduledDate;

    @Column(nullable = false,name = "garage_id")
    private Long garageId;


}
