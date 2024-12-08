package uni.project.rest.api.model;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGarageDTO {

    private String name;
    private String location;
    private String city;
    private Integer capacity;

}
