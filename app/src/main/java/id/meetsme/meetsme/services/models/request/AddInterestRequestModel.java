package id.meetsme.meetsme.services.models.request;

/**
 * Created by Ibam on 9/8/2017.
 */

public class AddInterestRequestModel {
    private String token;
    private String user;
    private String interest;

    public AddInterestRequestModel(String token, String user, String interest) {
        this.token = token;
        this.user = user;
        this.interest = interest;
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
}
