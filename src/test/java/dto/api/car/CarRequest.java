package dto.api.car;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CarRequest {

    @SerializedName("engineType")
    @Expose
    public String engineType;
    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("mark")
    @Expose
    public String mark;
    @SerializedName("model")
    @Expose
    public String model;
    @SerializedName("price")
    @Expose
    public Double price;
}