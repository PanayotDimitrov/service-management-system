package uni.project.rest.api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id")
    private Long id;

    @Column(nullable = false,name = "make")
    private String make;

    @Column(nullable = false,name = "model")
    private String model;

    @Column(nullable = false,name = "production_year")
    private Integer productionYear;

    @Column(nullable = false,name = "license_plate")
    private String licensePlate;

    @ManyToMany
    @JoinTable(
            name = "car_garage",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "garage_id")
    )
    private List<Garage> garages;

}

