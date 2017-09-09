package id.meetsme.meetsme.services.models.request;

/**
 * Created by Ibam on 9/10/2017.
 */

public class MatchMakingRequestModel {
    private String token;
    private String user;
    private String interest;
    private double location_lat;
    private double location_lang;

    public MatchMakingRequestModel(String token, String user, String interest, double location_lat, double location_lang) {

        this.token = token;
        this.user = user;
        this.interest = interest;
        this.location_lat = location_lat;
        this.location_lang = location_lang;
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

    public double getLocation_lat() {
        return location_lat;
    }

    public void setLocation_lat(double location_lat) {
        this.location_lat = location_lat;
    }

    public double getLocation_lang() {
        return location_lang;
    }

    public void setLocation_lang(double location_lang) {
        this.location_lang = location_lang;
    }
}
