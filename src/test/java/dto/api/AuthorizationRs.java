package dto.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationRs {
    @SerializedName("access_token")
    @Expose
    public String accessToken;
}
