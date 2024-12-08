package uni.project.rest.api.model;

import lombok.*;

@Getter
@Data
@Setter
@NoArgsConstructor
public class ResponseGarageDTO {

    private Long id;
    private String name;
    private String city;
    private int capacity;

    public ResponseGarageDTO(Long id, String name, String city, int capacity) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.capacity = capacity;
    }
}
