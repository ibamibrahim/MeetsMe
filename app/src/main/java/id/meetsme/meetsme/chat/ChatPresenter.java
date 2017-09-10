package id.meetsme.meetsme.chat;

import android.content.Context;

import id.meetsme.meetsme.services.RealmServices;
import id.meetsme.meetsme.services.models.chat.MessageModel;
import io.realm.RealmList;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ChatPresenter implements ChatContract.Presenter {

    ChatContract.View mView;


    @Override
    public void loadMessage(Context context, int user_id) {
        RealmList<MessageModel> messageModelRealmList = RealmServices.getAllMessage(user_id);
        mView.updateMessage(messageModelRealmList);
    }

    @Override
    public void setView(Object view) {
        mView = (ChatContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }
}
