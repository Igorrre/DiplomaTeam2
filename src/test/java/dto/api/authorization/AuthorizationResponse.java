package dto.api.authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationResponse {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
}
