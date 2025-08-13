package dto.ui.house;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class House {
    @Builder.Default
    Integer floor_send =0;
    @Builder.Default
    Integer price_send = 0;
    @Builder.Default
    Integer parking_first_send = 0;
    @Builder.Default
    Integer parking_second_send = 0;
    @Builder.Default
    Integer parking_third_send = 0;
    @Builder.Default
    Integer parking_fourth_send = 0;
}
