package id.meetsme.meetsme.services.models.response.register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterResponseModel {

    @SerializedName("user")
    @Expose
    private User user;
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("user_profile_id")
    @Expose
    private String user_profile_id;

    @SerializedName("device_token")
    @Expose
    private String device_token;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_profile_id() {
        return user_profile_id;
    }

    public void setUser_profile_id(String user_profile_id) {
        this.user_profile_id = user_profile_id;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }
}
