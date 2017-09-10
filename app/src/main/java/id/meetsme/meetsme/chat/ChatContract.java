package id.meetsme.meetsme.chat;

import android.content.Context;

import java.util.List;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;
import id.meetsme.meetsme.services.models.chat.MessageModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface ChatContract {

    interface View extends BaseView<Presenter> {
        void updateMessage(List<MessageModel> modelList);
    }

    interface Presenter extends BasePresenter {
        void loadMessage(Context context, int user_id);
    }
}
