package id.meetsme.meetsme.services;

import android.content.Context;

import java.util.List;

import id.meetsme.meetsme.services.models.chat.ChatModel;
import id.meetsme.meetsme.services.models.chat.MessageModel;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by Ibam on 9/10/2017.
 */

public class RealmServices {

    public static boolean createChatRoom(int user_id, String username, String lastMessage, Context
            context) {
        Realm realm = Realm.getDefaultInstance();
        // check if chat is not exist
        ChatModel chatRoom = realm.where(ChatModel.class).equalTo("user_id", user_id).findFirst();
        if (chatRoom == null) {
            realm.beginTransaction();
            ChatModel newChatRoom = realm.createObject(ChatModel.class);
            newChatRoom.setLastMessage(lastMessage);
            newChatRoom.setLastTimStamp("");
            newChatRoom.setUsername(username);
            newChatRoom.setUser_id(user_id);
            realm.commitTransaction();
            return true;
        }

        return false;
    }

    public static void createMessage(int user_id, String sender, String content, Context context) {

        Realm realm = Realm.getDefaultInstance();
        MessageModel newMessage = realm.createObject(MessageModel.class);
        newMessage.setContent(content);
        newMessage.setSender(sender);

        realm.beginTransaction();
        ChatModel chatRoom = realm.where(ChatModel.class).equalTo("user_id", user_id).findFirst();
        chatRoom.getMessages().add(newMessage);
        realm.commitTransaction();

    }

    public List<ChatModel> getAllChatRoom() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ChatModel> result = realm.where(ChatModel.class).findAll();
        result.size();
        return realm.copyFromRealm(result);
    }

    public RealmList<MessageModel> getAllMessage(int user_id) {
        Realm realm = Realm.getDefaultInstance();
        ChatModel chatRoom = realm.where(ChatModel.class).equalTo("user_id", user_id).findFirst();

        RealmList<MessageModel> allMessages = chatRoom.getMessages();

        return allMessages;
    }
}
