package id.meetsme.meetsme.services.models.request;

/**
 * Created by Ibam on 9/4/2017.
 */

public class LoginRequestModel {

    private String email;
    private String password;

    public LoginRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
