package uni.project.rest.api.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
@Setter
public class UpdateGarageDTO {

    private String name;
    private String location;
    private int capacity;
    private String city;

}
