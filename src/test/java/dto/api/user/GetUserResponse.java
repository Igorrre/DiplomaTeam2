package dto.api.user;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GetUserResponse {

    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("firstName")
    @Expose
    private String firstName;

    @SerializedName("secondName")
    @Expose
    private String secondName;

    @SerializedName("age")
    @Expose
    private int age;

    @SerializedName("sex")
    @Expose
    private String sex;

    @SerializedName("money")
    @Expose
    private double money;
}