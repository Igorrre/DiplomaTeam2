package dto.ui.house;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class House {
    Integer floor_send;
    Integer price_send;
    Integer parking_first_send;
    Integer parking_second_send;
    Integer parking_third_send;
    Integer parking_fourth_send;
}
