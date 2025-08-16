package dto.api.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {

    @SerializedName("firstName")
    @Expose
    public String firstName;
    @SerializedName("secondName")
    @Expose
    public String secondName;
    @SerializedName("age")
    @Expose
    public int age;
    @SerializedName("sex")
    @Expose
    public String sex;
    @SerializedName("money")
    @Expose
    public int money;
}