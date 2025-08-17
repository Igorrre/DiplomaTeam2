package dto.api.house;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
public class HouseResponse {

    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("floorCount")
    @Expose
    public int floorCount;
    @SerializedName("price")
    @Expose
    public int price;
    @SerializedName("parkingPlaces")
    @Expose
    @Singular("parkingPlaces")
    public List<ParkingPlaceResponse> parkingPlaces;
    @SerializedName("lodgers")
    @Expose
    @Singular("lodgers")
    public List<LodgerResponse> lodgers;
}