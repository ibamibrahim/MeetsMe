package id.meetsme.meetsme.services.models.response.matchmaking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchMakingResponse {

    @SerializedName("user")
    @Expose
    private Integer user;
    @SerializedName("location_lat")
    @Expose
    private Double locationLat;
    @SerializedName("location_lon")
    @Expose
    private Double locationLon;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(Double locationLon) {
        this.locationLon = locationLon;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

}
