package dto.api.authorization;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorizationRequest {
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("username")
    @Expose
    public String username;
}
