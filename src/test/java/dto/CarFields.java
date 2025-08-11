package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class CarFields {
    @Builder.Default
    String engine = "";
    @Builder.Default
    String mark = "";
    @Builder.Default
    String model = "";
    @Builder.Default
    int price = Integer.parseInt("");
}