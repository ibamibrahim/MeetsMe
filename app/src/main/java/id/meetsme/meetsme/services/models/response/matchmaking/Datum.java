
package id.meetsme.meetsme.services.models.response.matchmaking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("interest")
    @Expose
    private Interest interest;
    @SerializedName("pair")
    @Expose
    private Pair pair;
    @SerializedName("weight")
    @Expose
    private Double weight;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public Pair getPair() {
        return pair;
    }

    public void setPair(Pair pair) {
        this.pair = pair;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

}
