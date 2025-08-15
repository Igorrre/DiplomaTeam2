package dto.api.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CreateUserResponse {

    @SerializedName("id")
    @Expose
    public int id;
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