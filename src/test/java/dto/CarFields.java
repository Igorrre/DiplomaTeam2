package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class CarFields {
    @Builder.Default
    String engine = "Electric";
    @Builder.Default
    String mark = "tesla";
    @Builder.Default
    String model = "model s";
    @Builder.Default
    int price = Integer.parseInt("3333");
}