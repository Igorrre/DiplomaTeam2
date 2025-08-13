package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class HouseFields{
    int floor_send;
    int price_send;
    int parking_first_send;
    int parking_second_send;
    int parking_third_send;
    int parking_fourth_send;
}
