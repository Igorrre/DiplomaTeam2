package dto.ui.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder

public class Car {
    @Builder.Default
    String engine = "";
    @Builder.Default
    String mark = "";
    @Builder.Default
    String model = "";
    @Builder.Default
    Integer price = 0;
}