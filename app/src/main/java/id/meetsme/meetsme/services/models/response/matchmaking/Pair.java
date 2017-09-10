
package id.meetsme.meetsme.services.models.response.matchmaking;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pair {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("interest")
    @Expose
    private String interest;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

}
