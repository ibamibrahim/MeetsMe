package id.meetsme.meetsme.services.models.request;

/**
 * Created by Ibam on 9/8/2017.
 */

public class AddInterestRequestModel {
    private String token;
    private String user;
    private String interets;

    public AddInterestRequestModel(String token, String user, String interets) {
        this.token = token;
        this.user = user;
        this.interets = interets;
    }
}
