
package id.meetsme.meetsme.services.models.response.matchmaking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("location_lat")
    @Expose
    private Object locationLat;
    @SerializedName("location_lon")
    @Expose
    private Object locationLon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Object getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Object locationLat) {
        this.locationLat = locationLat;
    }

    public Object getLocationLon() {
        return locationLon;
    }

    public void setLocationLon(Object locationLon) {
        this.locationLon = locationLon;
    }

}
