package id.meetsme.meetsme.services.models.chat;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by Ibam on 9/10/2017.
 */

public class ChatList extends RealmObject {
    private int user_id;
    private String username;
    private String lastMessage;
    private String lastTimStamp;
    private RealmList<MessageModel> messages;


    public ChatList(int user_id, String username, String lastMessage, String lastTimStamp) {

        this.user_id = user_id;
        this.username = username;
        this.lastMessage = lastMessage;
        this.lastTimStamp = lastTimStamp;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getLastTimStamp() {
        return lastTimStamp;
    }

    public void setLastTimStamp(String lastTimStamp) {
        this.lastTimStamp = lastTimStamp;
    }
}
