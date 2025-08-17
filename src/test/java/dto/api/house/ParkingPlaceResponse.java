package dto.api.house;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ParkingPlaceResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("isCovered")
    @Expose
    public Boolean isCovered;
    @SerializedName("isWarm")
    @Expose
    public Boolean isWarm;
    @SerializedName("placesCount")
    @Expose
    public int placesCount;
}