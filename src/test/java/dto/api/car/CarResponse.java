package dto.api.car;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CarResponse {

    @SerializedName("engineType")
    @Expose
    public String engineType;
    @SerializedName("id")
    @Expose
    public int id;
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