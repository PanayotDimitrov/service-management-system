package uni.project.rest.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CreateGarageDTO {

    private String name;
    private String location;
    private String city;
    private int capacity;

}
