package id.meetsme.meetsme.messagelist;

import java.util.List;

import id.meetsme.meetsme.BasePresenter;
import id.meetsme.meetsme.BaseView;
import id.meetsme.meetsme.services.models.chat.ChatModel;
import id.meetsme.meetsme.services.models.response.MessageListModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public interface MessageListContract {

    interface View extends BaseView<Presenter> {
        void updateMessageList(List<ChatModel> list);
    }

    interface Presenter extends BasePresenter {
        void loadMessage();
    }
}
