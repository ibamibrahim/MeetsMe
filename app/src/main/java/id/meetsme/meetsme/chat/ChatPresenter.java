package id.meetsme.meetsme.chat;

import android.content.Context;
import android.util.Log;

import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.services.LocalServices;
import id.meetsme.meetsme.services.RealmServices;
import id.meetsme.meetsme.services.RemoteServices;
import id.meetsme.meetsme.services.models.chat.MessageModel;
import id.meetsme.meetsme.services.models.response.ChatIncomingModel;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;
import io.realm.RealmList;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Ibam on 8/28/2017.
 */

public class ChatPresenter implements ChatContract.Presenter {

    private static final String TAG = "ChatPresenter";
    ChatContract.View mView;

    @Override
    public void loadMessage(Context context, int user_id) {
        RealmList<MessageModel> messageModelRealmList = RealmServices.getAllMessage(user_id);
        mView.updateMessage(messageModelRealmList);
    }

    @Override
    public void sendMessage(int sender_id, int recipient_id, String message, Context context) {
        String token = LocalServices.getToken(context);

        saveToRealm(recipient_id, message, context);

        RemoteServices remoteServices = new RemoteServices();
        remoteServices.sendChat(token, sender_id + "", recipient_id + "", message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Response<ChatIncomingModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<ChatIncomingModel> response) {
                        if (response.isSuccessful()) {
                            Log.i(TAG, "onNext: " + response.body().toString());
                        } else {
                            Log.i(TAG, "onNext: " + response.toString());
                            mView.notifyAdapter();
                        }
                    }
                });
    }

    private void saveToRealm(int rec_id, String message, Context context) {
        String userDetail = LocalServices.getUserDetail(context);
        LoginResponseModel user = Helper.jsonToObject(userDetail, LoginResponseModel.class);
        RealmServices.createMessage(rec_id, user.getUser().getUser().getFirstName(), message);
    }

    @Override
    public void setView(Object view) {
        mView = (ChatContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }
}
