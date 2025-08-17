package dto.api.house;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingPlaceRequest {

    @SerializedName("id")
    @Expose
    public Integer id;
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