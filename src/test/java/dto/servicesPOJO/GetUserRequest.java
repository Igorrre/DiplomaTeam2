package dto.servicesPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetUserRequest {

    @SerializedName("userId")
    @Expose
    public String userId;
}