package id.meetsme.meetsme.services.models.chat;

import io.realm.RealmObject;

/**
 * Created by Ibam on 9/10/2017.
 */

public class MessageModel extends RealmObject {
    private String sender;
    private String content;

    public MessageModel() {
    }

    public MessageModel(String sender, String content) {

        this.sender = sender;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
