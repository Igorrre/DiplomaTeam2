package dto.api.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarResponse {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("engineType")
    private String engineType;

    @JsonProperty("mark")
    private String mark;

    @JsonProperty("model")
    private String model;

    @JsonProperty("price")
    private Double price;
}