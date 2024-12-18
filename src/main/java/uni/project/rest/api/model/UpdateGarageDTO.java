package uni.project.rest.api.model;

import lombok.*;

@Getter
@Data
@Setter
@NoArgsConstructor
public class UpdateGarageDTO {

    private String name;
    private String location;
    private int capacity;
    private String city;

}
