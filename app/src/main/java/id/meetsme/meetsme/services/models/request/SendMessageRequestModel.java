package id.meetsme.meetsme.services.models.request;

/**
 * Created by Ibam on 9/10/2017.
 */

public class SendMessageRequestModel {
    private String user;
    private String recipient;
    private String message;
    private String token;

    public SendMessageRequestModel(String user, String recipient, String message, String token) {

        this.user = user;
        this.recipient = recipient;
        this.message = message;
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
