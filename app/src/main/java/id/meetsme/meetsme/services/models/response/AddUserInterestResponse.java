package id.meetsme.meetsme.services.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibam on 9/10/2017.
 */

public class AddUserInterestResponse {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("interest")
    @Expose
    private String interest;

    public AddUserInterestResponse(String user, String interest) {

        this.user = user;
        this.interest = interest;
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
}
