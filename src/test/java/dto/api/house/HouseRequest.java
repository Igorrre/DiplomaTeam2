package dto.api.house;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class HouseRequest {

    @SerializedName("floorCount")
    @Expose
    public int floorCount;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("parkingPlaces")
    @Expose
    @Singular("parkingPlaces")
    public List<ParkingPlaceRequest> parkingPlaces;
    @SerializedName("price")
    @Expose
    public int price;
}