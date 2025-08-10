package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserFields {

    @Builder.Default
    String firstName = "";
    @Builder.Default
    String lastName = "";
    @Builder.Default
    int age = Integer.parseInt("");
    @Builder.Default
    int money = Integer.parseInt("");
}