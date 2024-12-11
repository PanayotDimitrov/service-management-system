package uni.project.rest.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "garage")
public class Garage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,name = "id")
    private Long id;

    @Column(nullable = false,name = "name")
    private String name;

    @Column(nullable = false,name = "location")
    private String location;

    @Column(nullable = false,name = "city")
    private String city;

    @Column(nullable = false,name = "capacity")
    private Integer capacity;


}
