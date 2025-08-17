package dto.api.house;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class LodgerResponse {

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
    public Integer age;
    @SerializedName("sex")
    @Expose
    public String sex;
    @SerializedName("money")
    @Expose
    public Integer money;

}
