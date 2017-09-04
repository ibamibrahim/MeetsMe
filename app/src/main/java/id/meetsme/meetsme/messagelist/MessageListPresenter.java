package id.meetsme.meetsme.messagelist;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import id.meetsme.meetsme.services.models.response.MessageListModel;

/**
 * Created by Ibam on 8/28/2017.
 */

public class MessageListPresenter implements MessageListContract.Presenter {

    MessageListContract.View mView;

    @Override
    public void setView(Object view) {
        this.mView = (MessageListContract.View) view;
    }

    @Override
    public void unsetView(Object view) {

    }

    @Override
    public void loadMessage() {
        Random random = new Random();
        List<MessageListModel> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            MessageListModel model = new MessageListModel();
            model.setName(randomString(8));
            model.setLast_message(randomString(15));
            model.setUnread_count(i + "");
            model.setTime_stamp("21.10");
            list.add(model);
        }
        mView.updateMessageList(list);
    }

    private java.lang.String randomString(int length) {
        return UUID.randomUUID().toString().substring(0, length);
    }
}
