package dto.ui.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Users {

    @Builder.Default
    String firstName = "";
    @Builder.Default
    String lastName = "";
    @Builder.Default
    Integer age = 0;
    @Builder.Default
    Integer money = 0;
}