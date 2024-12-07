package uni.project.rest.api.model;

import lombok.*;

@Getter
@Data
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseGarageDTO {

    private Long id;
    private String name;
    private String city;
    private int capacity;



}
