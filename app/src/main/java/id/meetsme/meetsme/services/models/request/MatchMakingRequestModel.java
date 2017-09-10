package id.meetsme.meetsme.services.models.request;

/**
 * Created by Ibam on 9/10/2017.
 */

public class MatchMakingRequestModel {
    private String token;
    private String user;
    private String interest;
    private String location_lat;
    private String location_lon;

    public MatchMakingRequestModel(String token, String user, String interest, String location_lat, String location_lon) {

        this.token = token;
        this.user = user;
        this.interest = interest;
        this.location_lat = location_lat;
        this.location_lon = location_lon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(String location_lat) {
        this.location_lat = location_lat;
    }

    public String getLocation_lon() {
        return location_lon;
    }

    public void setLocation_lon(String location_lon) {
        this.location_lon = location_lon;
    }
}
