package uni.project.rest.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseGarageDTO {

    private long id;
    private String name;
    private String city;
    private int capacity;

}
