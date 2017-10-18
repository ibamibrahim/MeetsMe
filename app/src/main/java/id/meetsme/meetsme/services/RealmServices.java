package id.meetsme.meetsme.services;

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

    public static boolean createChatRoom(int user_id, String username, String lastMessage) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        // check if chat is not exist
        ChatModel chatRoom = realm.where(ChatModel.class).equalTo("user_id", user_id).findFirst();
        if (chatRoom == null) {
            ChatModel newChatRoom = realm.createObject(ChatModel.class);
            newChatRoom.setLastMessage(lastMessage);
            newChatRoom.setLastTimStamp("");
            newChatRoom.setUsername(username);
            newChatRoom.setUser_id(user_id);
            realm.commitTransaction();
            return true;
        }
        realm.commitTransaction();
        return false;
    }

    public static void createMessage(int user_id, String sender, String content) {

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        MessageModel newMessage = realm.createObject(MessageModel.class);
        newMessage.setContent(content);
        newMessage.setSender(sender);

        ChatModel chatRoom = realm.where(ChatModel.class).equalTo("user_id", user_id).findFirst();
        chatRoom.getMessages().add(newMessage);
        realm.commitTransaction();

    }

    public static List<ChatModel> getAllChatRoom() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ChatModel> result = realm.where(ChatModel.class).findAll();
        result.size();
        return realm.copyFromRealm(result);
    }

    public static RealmList<MessageModel> getAllMessage(int user_id) {
        Realm realm = Realm.getDefaultInstance();
        ChatModel chatRoom = realm.where(ChatModel.class).equalTo("user_id", user_id).findFirst();

        RealmList<MessageModel> allMessages = chatRoom.getMessages();

        return allMessages;
    }

    public static String getRandomName(int user_id) {
        String result = null;
        Realm realm = Realm.getDefaultInstance();
        ChatModel model = realm.where(ChatModel.class).equalTo("user_id",user_id).findFirst();
        if(model != null){
            result = model.getUsername();
        }
        return result;
    }
}
