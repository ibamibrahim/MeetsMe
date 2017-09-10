package id.meetsme.meetsme.services.models;

/**
 * Created by Ibam on 9/10/2017.
 */

public class MapsModel {
    private int id;
    private String name;
    private String occupation;
    private String matchedInterest;
    private double weight;
    private String lon;
    private String lat;

    public MapsModel(int id, String name, String occupation, String matchedInterest, double weight) {

        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.matchedInterest = matchedInterest;
        this.weight = weight;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMatchedInterest() {
        return matchedInterest;
    }

    public void setMatchedInterest(String matchedInterest) {
        this.matchedInterest = matchedInterest;
    }
}
